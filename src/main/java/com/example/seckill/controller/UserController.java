package com.example.seckill.controller;

import com.example.seckill.controller.viewobject.UserVO;
import com.example.seckill.error.BusinessException;
import com.example.seckill.error.EmBusinessError;
import com.example.seckill.response.CommonReturnType;
import com.example.seckill.service.UserService;
import com.example.seckill.service.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author cheng
 * @date 2022-04-29 22:09:13
 */
@Controller
@RequestMapping("/user")
@Slf4j
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/login", method = {RequestMethod.POST},
            consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telephone") String telephone,
                                  @RequestParam(name = "password") String password) throws BusinessException, NoSuchAlgorithmException {
        // 入参校验
        if (StringUtils.isEmpty(telephone))
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号不能为空");
        if (StringUtils.isEmpty(password))
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "密码不能为空");
        // 用户登录服务，校验登录是否合法
        UserModel userModel = userService.validateLogin(telephone, EncodeByMd5(password));
        // 没有抛异常，说明登录成功，将登陆凭证加入session，后续会做分布式session
        httpServletRequest.getSession().setAttribute("IS_LOGGED_IN", true);
        httpServletRequest.getSession().setAttribute("LOGGED_IN_USER", userModel);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST},
            consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(
            @RequestParam(name = "telephone") String telephone,
            @RequestParam(name = "otpCode") String otpCode,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "gender") Integer gender,
            @RequestParam(name = "age") Integer age,
            @RequestParam(name = "password") String password
    ) throws Exception {
        // 验证手机号和otpCode是否相符
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telephone);
        if (!StringUtils.equals(otpCode, inSessionOtpCode)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "otp验证码错误");
        }
        // 后续注册
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setGender(gender.byteValue());
        userModel.setTelephone(telephone);
        userModel.setRegisterMode("byphone");
        userModel.setEncryptPassword(EncodeByMd5(password));

        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    private String EncodeByMd5(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();

        String encryptPassword = base64Encoder.encode(md5.digest(password.getBytes(StandardCharsets.UTF_8)));
        return encryptPassword;
    }

    @RequestMapping(value = "/getotp", method = {RequestMethod.POST},
            consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telephone") String telephone) {
        // 需要按照一定的规则生成OTP验证码
        // 想生成一个5位数的验证码，[10000 - 99999]
        int randomNumber = 10000 + (int) (Math.random() * 90000);
        String otpCode = String.valueOf(randomNumber);

        // 将OTP验证码同用户的手机号相关联
        // 未采用分布式，后期会改成分布式，并使用Redis存放验证码
        // httpServletRequest 里有threadlocal保证多线程并发的安全性
        httpServletRequest.getSession().setAttribute(telephone, otpCode);

        // 将OTP验证码通过短信发送给用户，省略
        log.info("telephone = " + telephone + " & otpCode = " + otpCode);
        return CommonReturnType.create(null);
    }


    /**
     * 调用service服务获取对应id的用户，返回给前端
     * 为什么是void？
     *
     * @param id
     */
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) return null;
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }

}
