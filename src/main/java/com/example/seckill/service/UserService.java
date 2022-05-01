package com.example.seckill.service;

import com.example.seckill.error.BusinessException;
import com.example.seckill.service.model.UserModel;

public interface UserService {
    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws Exception;

    /**
     * 验证手机密码是否正确
     *
     * @param telephone       手机号
     * @param encryptPassword 加密后的密码
     * @return
     * @throws BusinessException
     */
    UserModel validateLogin(String telephone, String encryptPassword) throws BusinessException;
}
