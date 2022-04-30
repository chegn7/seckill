package com.example.seckill.service;

import com.example.seckill.service.model.UserModel;

public interface UserService {
    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws Exception;
}
