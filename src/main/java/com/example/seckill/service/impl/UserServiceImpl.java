package com.example.seckill.service.impl;

import com.example.seckill.dao.UserDOMapper;
import com.example.seckill.dao.UserPasswordDOMapper;
import com.example.seckill.dataobject.UserDO;
import com.example.seckill.dataobject.UserPasswordDO;
import com.example.seckill.error.BusinessException;
import com.example.seckill.error.EmBusinessError;
import com.example.seckill.service.UserService;
import com.example.seckill.service.model.UserModel;
import com.example.seckill.validator.ValidationResult;
import com.example.seckill.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO == null) return null;
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO, userPasswordDO);
        return userModel;
    }



    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        if (userModel == null) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        // 校验用户信息的必填项
//        if (StringUtils.isEmpty(userModel.getName())
//                || userModel.getGender() == null
//                || userModel.getAge() == null
//                || StringUtils.isEmpty(userModel.getTelephone())
////                || StringUtils.isEmpty(userModel.getRegisterMode())
////                || StringUtils.isEmpty(userModel.getThirdPartyId())
//        ) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
//
        ValidationResult validationResult = validator.validate(userModel);
        if(validationResult.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, validationResult.getErrorMessage());
        }

        UserDO userDO = convertFromModel(userModel);
        try {
            userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号已注册，请登录");
        }

        userModel.setId(userDO.getId());

        UserPasswordDO userPasswordDO = convertFromModelToPassword(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
        return;
    }

    @Override
    public UserModel validateLogin(String telephone, String encryptPassword) throws BusinessException {
        UserDO userDO = userDOMapper.selectByTelephone(telephone);
        if (userDO == null) throw new BusinessException(EmBusinessError.USER_LOGIN_FAILED);
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO,userPasswordDO);
        if (!StringUtils.equals(encryptPassword,userModel.getEncryptPassword()))throw new BusinessException(EmBusinessError.USER_LOGIN_FAILED);
        return userModel;
    }

    private UserPasswordDO convertFromModelToPassword(UserModel userModel) {
        if (userModel == null) return null;
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setUserId(userModel.getId());
        userPasswordDO.setEncryptPassword(userModel.getEncryptPassword());
        return userPasswordDO;
    }

    private UserDO convertFromModel(UserModel userModel) {
        if (userModel == null) return null;
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel,userDO);
        return userDO;
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        if (userPasswordDO != null) userModel.setEncryptPassword(userPasswordDO.getEncryptPassword());
        return userModel;
    }
}
