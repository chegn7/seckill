package com.example.seckill.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserPasswordDOMapperTest {
    @Autowired
    UserPasswordDOMapper userPasswordDOMapper;

    @Test
    void selectByUserId() {
        System.out.println(userPasswordDOMapper.selectByUserId(1));
    }

    @Test
    void selectById() {
        System.out.println(userPasswordDOMapper.selectByPrimaryKey(1));
    }
}