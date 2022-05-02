package com.example.seckill.dao;

import com.example.seckill.dataobject.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
@Slf4j
class OrderDOMapperTest {
    @Autowired
    OrderDOMapper orderDOMapper;

    @Test
    void selectById() {
        OrderDO orderDO = orderDOMapper.selectById("20220501001");
        System.out.println(orderDO);
    }
}