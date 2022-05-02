package com.example.seckill.service.impl;

import com.example.seckill.dao.OrderDOMapper;
import com.example.seckill.dao.SequenceDOMapper;
import com.example.seckill.dataobject.OrderDO;
import com.example.seckill.dataobject.SequenceDO;
import com.example.seckill.error.BusinessException;
import com.example.seckill.error.EmBusinessError;
import com.example.seckill.service.ItemService;
import com.example.seckill.service.OrderService;
import com.example.seckill.service.PromoService;
import com.example.seckill.service.UserService;
import com.example.seckill.service.model.ItemModel;
import com.example.seckill.service.model.OrderModel;
import com.example.seckill.service.model.PromoModel;
import com.example.seckill.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author cheng
 * @date 2022-05-02 09:04:13
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Autowired
    private PromoService promoService;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException {
        // 1. 校验下单状态：商品状态是否可售，用户是否合法，数量是否正确
        ItemModel item = itemService.getItem(itemId);
        if (item == null) throw new BusinessException(EmBusinessError.ITEM_NOT_EXIST);
        UserModel user = userService.getUserById(userId);
        if (user == null) throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        if (amount <= 0 || amount > 99)
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "购买数量不合法");
        // 校验促销活动信息
        PromoModel promo = item.getPromoModel();
        if (promoId != null) {
            // (1) 商品是否存在该促销活动
            if (promo == null || promo.getItemId() != itemId) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "活动信息错误");
            // (2) 校验活动是否正在进行
            if (promo.getStatus() != 2) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "活动未开始或已结束");
        }

        // 2. 本项目落单减库存，有些项目是支付减库存
        Boolean decreaseStock = itemService.decreaseStock(itemId, amount);
        if (!decreaseStock) throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);

        // 3. 订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setPromoId(promoId);

        if (promoId == null) orderModel.setItemPrice(item.getPrice());
        else orderModel.setItemPrice(promo.getPromoItemPrice());

        orderModel.setAmount(amount);
        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(BigDecimal.valueOf(amount)));

        // 生成订单流水号
        String orderId = generateOrderNo();
        orderModel.setId(orderId);
        OrderDO orderDO = convertModelToDO(orderModel);
        orderDOMapper.insertSelective(orderDO);
        orderModel.setId(orderDO.getId());

        // 增加商品的销量
        // 2.2 增加销量
        Boolean increaseSales = itemService.increaseSales(itemId, amount);
        if (!increaseSales) throw new BusinessException(EmBusinessError.SALES_NOT_INCREASED);
        // 4. 返回前端
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    String generateOrderNo() {
        // 假设16位订单号
        StringBuilder stringBuilder = new StringBuilder();
        // 8位 年月日 20220502
        LocalDateTime now = LocalDateTime.now();
        String dateString = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        stringBuilder.append(dateString);
        // 6位 自增序列
        // 获取当前sequence
        SequenceDO sequenceDO = sequenceDOMapper.selectSequenceDOByName("order_info");
        int number = sequenceDO.getCurrentValue();
        String numberStr = String.valueOf(number);
        for (int i = 0; i < 6 - numberStr.length(); i++) {
            stringBuilder.append(0);
        }
        stringBuilder.append(numberStr);
        // 循环自增
        number += sequenceDO.getStep();
        if (number > 1e7) number = 0;
        sequenceDO.setCurrentValue(number);
        sequenceDOMapper.updateSequenceDO(sequenceDO);
        // 2位 分库分表位
        // 暂时写死为00
        stringBuilder.append("00");
        return stringBuilder.toString();
    }

    private OrderDO convertModelToDO(OrderModel orderModel) {
        if (orderModel == null) return null;
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        return orderDO;
    }
}
