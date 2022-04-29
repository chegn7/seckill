package com.example.seckill.dao;

import com.example.seckill.dataobject.UserDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserDOMapperTest {
    @Autowired
    UserDOMapper userDOMapper;
    @Test
    void selectByPrimaryKey() {
        UserDO userDO = userDOMapper.selectByPrimaryKey(1);
        if (userDO == null) {
            System.out.println("用户不存在");
        } else {
            System.out.println(userDO.getName());
        }
    }
}