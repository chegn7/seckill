package com.example.seckill.service.impl;

import com.example.seckill.error.BusinessException;
import com.example.seckill.service.ItemService;
import com.example.seckill.service.model.ItemModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
@SpringBootTest
class ItemServiceImplTest {
    @Autowired
    ItemService itemService;

    @Test
    void createItemModel() throws BusinessException {
        for (int i = 0; i < 100; i++) {
            ItemModel itemModel = new ItemModel();
            itemModel.setTitle("title " + i);
            itemModel.setSales(0);
            itemModel.setStock((int) (Math.random() * 1000));
            itemModel.setDescription("description " + i);
            itemModel.setPrice(BigDecimal.valueOf(Math.random() + i));
            itemModel.setImgUrl("url " + i);
            itemService.createItemModel(itemModel);
        }
    }

    @Test
    void listItem() {
    }

    @Test
    void getItem() {
    }

    @Test
    void decreaseStock() {
        Boolean res = itemService.decreaseStock(4, 5);
        System.out.println(res);
    }

    @Test
    void increaseSales() {
        System.out.println(itemService.increaseSales(4, 1));
    }
}