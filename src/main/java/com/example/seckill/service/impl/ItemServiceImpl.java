package com.example.seckill.service.impl;

import com.example.seckill.dao.ItemDOMapper;
import com.example.seckill.dao.ItemStockDOMapper;
import com.example.seckill.dataobject.ItemDO;
import com.example.seckill.dataobject.ItemStockDO;
import com.example.seckill.error.BusinessException;
import com.example.seckill.error.EmBusinessError;
import com.example.seckill.service.ItemService;
import com.example.seckill.service.model.ItemModel;
import com.example.seckill.validator.ValidationResult;
import com.example.seckill.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cheng
 * @date 2022-05-01 18:06:27
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private ItemStockDOMapper itemStockDOMapper;

    @Override
    @Transactional
    public ItemModel createItemModel(ItemModel itemModel) throws BusinessException {
        // 校验入参信息
        ValidationResult validationResult = validator.validate(itemModel);
        if (validationResult.isHasErrors())
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, validationResult.getErrorMessage());
        // model -> dataobject
        ItemDO itemDO = convertFromItemModelToItemDO(itemModel);
        itemDOMapper.insertSelective(itemDO);
        itemModel.setId(itemDO.getId());
        ItemStockDO itemStockDO = convertFromItemModelToItemStockDO(itemModel);
        // 写入数据库
        itemStockDOMapper.insertSelective(itemStockDO);
        return getItem(itemModel.getId());
    }

    private ItemStockDO convertFromItemModelToItemStockDO(ItemModel itemModel) {
        if (itemModel == null) return null;
        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setStock(itemModel.getStock());
        itemStockDO.setItemId(itemModel.getId());
        return itemStockDO;
    }

    private ItemDO convertFromItemModelToItemDO(ItemModel itemModel) {
        if (itemModel == null) return null;
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel, itemDO);
        return itemDO;
    }

    @Override
    public List<ItemModel> listItem() {
        List<ItemDO> itemDOList = itemDOMapper.listItem();
        List<ItemModel> itemModelList = new ArrayList<>();
        if (itemDOList == null || itemDOList.size() == 0) return itemModelList;
        for (ItemDO itemDO : itemDOList) {
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            ItemModel itemModel = convertFromDataObject(itemDO, itemStockDO);
            itemModelList.add(itemModel);
        }
//        List<ItemModel> itemModelList1 = itemDOList.stream().map(
//                itemDO -> {
//                    ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
//                    ItemModel itemModel = convertFromDataObject(itemDO, itemStockDO);
//                    return itemModel;
//                }
//        ).collect(Collectors.toList());

        return itemModelList;
    }

    @Override
    public ItemModel getItem(Integer itemId) {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(itemId);
        if (itemDO == null) return null;
        // 获得库存
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
        ItemModel itemModel = convertFromDataObject(itemDO, itemStockDO);
        return itemModel;
    }

    private ItemModel convertFromDataObject(ItemDO itemDO, ItemStockDO itemStockDO) {
        if (itemDO == null || itemStockDO == null) return null;
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO, itemModel);
        itemModel.setStock(itemStockDO.getStock());
        return itemModel;
    }
}
