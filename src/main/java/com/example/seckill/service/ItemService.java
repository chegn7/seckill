package com.example.seckill.service;

import com.example.seckill.error.BusinessException;
import com.example.seckill.service.model.ItemModel;

import java.util.List;

public interface ItemService {
    // 创建商品
    ItemModel createItemModel(ItemModel itemModel) throws BusinessException;
    // 获取商品列表
    List<ItemModel>  listItem();
    // 获取商品详情
    ItemModel getItem(Integer itemId);

    Boolean decreaseStock(Integer itemId, Integer amount);

    Boolean increaseSales(Integer itemId, Integer amount);
}
