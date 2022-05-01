package com.example.seckill.service.impl;

import com.example.seckill.service.UserService;
import com.example.seckill.service.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    void getUserById() {
    }

    @Test
    void register() throws Exception {
        for (int i = 0; i < 100; i++) {
            UserModel userModel = new UserModel();
            userModel.setName("aaa" + i);
            userModel.setTelephone(String.valueOf(12300000001L + i));
            userModel.setRegisterMode("byphone");
            userModel.setGender((byte) (1 + (int) (Math.random() * 2)));
            userModel.setAge((int) (Math.random() * 80) + 18);
            userModel.setEncryptPassword("4QrcOUm6Wau+VuBX8g+IPg==");// 123456加密后
            userService.register(userModel);

        }
    }

    @Test
    void validateLogin() {
    }
}