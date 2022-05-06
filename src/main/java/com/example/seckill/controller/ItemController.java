package com.example.seckill.controller;

import com.example.seckill.common.RedisKeyUtil;
import com.example.seckill.controller.viewobject.ItemVO;
import com.example.seckill.error.BusinessException;
import com.example.seckill.error.EmBusinessError;
import com.example.seckill.response.CommonReturnType;
import com.example.seckill.service.ItemService;
import com.example.seckill.service.model.ItemModel;
import com.example.seckill.service.model.PromoModel;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author cheng
 * @date 2022-05-01 18:28:48
 */
@Controller
@RequestMapping("/item")
@Slf4j
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class ItemController extends BaseController {

    @Autowired
    ItemService itemService;

    @Autowired
    RedisTemplate redisTemplate;


    @RequestMapping(value = "/create", method = {RequestMethod.POST},
            consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    // 创建商品
    public CommonReturnType createItem(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "price") BigDecimal price,
            @RequestParam(name = "stock") Integer stock,
            @RequestParam(name = "imgUrl") String imgUrl
    ) throws BusinessException {
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);
        itemModel.setSales(0);

        ItemModel itemModelForReturn = itemService.createItemModel(itemModel);

        ItemVO itemVO = convertVOFromModel(itemModelForReturn);
        return CommonReturnType.create(itemVO);
    }

    // 商品详情
    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam(name = "itemId") Integer itemId) throws BusinessException {
        String itemDetailKey = RedisKeyUtil.getItemDetailKey(itemId);
        ItemModel itemModel = (ItemModel) redisTemplate.opsForValue().get(itemDetailKey);
        if (itemModel == null) {
            itemModel = itemService.getItem(itemId);
            redisTemplate.opsForValue().set(itemDetailKey, itemModel, 10, TimeUnit.MINUTES);
        }
        if (itemModel == null) throw new BusinessException(EmBusinessError.ITEM_NOT_EXIST);
        ItemVO itemVO = convertVOFromModel(itemModel);
        return CommonReturnType.create(itemVO);
    }

    // 商品列表
    @RequestMapping(value = "/list-item", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItemList() {
        List<ItemModel> itemModelList = itemService.listItem();
        List<ItemVO> itemVOList = itemModelList.stream().map(itemModel -> convertVOFromModel(itemModel)).collect(Collectors.toList());
        return CommonReturnType.create(itemVOList);
    }

    private ItemVO convertVOFromModel(ItemModel itemModel) {
        if (itemModel == null) return null;
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        PromoModel promoModel = itemModel.getPromoModel();
        if (promoModel != null) {
            itemVO.setPromoStatus(promoModel.getStatus());
            itemVO.setPromoId(promoModel.getId());
            itemVO.setPromoPrice(promoModel.getPromoItemPrice());
            itemVO.setStartDate(promoModel.getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
//            itemVO.setStartDate(promoModel.getStartDate().toString());
        } else itemVO.setPromoStatus(0);
        return itemVO;
    }
}
