package com.example.seckill.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class PromoDOMapperTest {
    @Autowired
    PromoDOMapper promoDOMapper;

    @Test
    void selectByPromoId() {
        System.out.println(promoDOMapper.selectByPromoId(1));
    }

    @Test
    void selectByItemId() {
        System.out.println(promoDOMapper.selectByItemId(4));
    }
}