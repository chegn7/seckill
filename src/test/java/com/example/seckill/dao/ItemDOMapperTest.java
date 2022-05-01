package com.example.seckill.dao;

import com.example.seckill.dataobject.ItemDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ItemDOMapperTest {
    @Autowired
    ItemDOMapper itemDOMapper;

    @Test
    void insertSelective() {
        ItemDO itemDO = new ItemDO();
        itemDO.setTitle("test item 2");
        itemDO.setPrice(BigDecimal.valueOf(2.34));
        itemDO.setSales(32);
        itemDO.setDescription("test text");
        itemDO.setImgUrl("xxx.xx.xx");
        itemDOMapper.insertSelective(itemDO);
        System.out.println(itemDO);
    }

    @Test
    void selectByPrimaryKey() {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(2);
        System.out.println(itemDO);
    }
}