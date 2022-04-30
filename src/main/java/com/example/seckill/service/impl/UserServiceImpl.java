package com.example.seckill.service.impl;

import com.example.seckill.dao.UserDOMapper;
import com.example.seckill.dao.UserPasswordDOMapper;
import com.example.seckill.dataobject.UserDO;
import com.example.seckill.dataobject.UserPasswordDO;
import com.example.seckill.service.UserService;
import com.example.seckill.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cheng
 * @date 2022-04-29 22:12:38
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO == null) return null;
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO, userPasswordDO);
        return userModel;
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        if (userPasswordDO != null) userModel.setEncryptPassword(userPasswordDO.getEncryptPassword());
        return userModel;
    }
}
