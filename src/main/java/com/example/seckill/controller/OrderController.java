package com.example.seckill.controller;

import com.example.seckill.error.BusinessException;
import com.example.seckill.error.EmBusinessError;
import com.example.seckill.response.CommonReturnType;
import com.example.seckill.service.OrderService;
import com.example.seckill.service.model.OrderModel;
import com.example.seckill.service.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cheng
 * @date 2022-05-02 10:34:32
 */
@Controller
@RequestMapping("/order")
@Slf4j
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class OrderController extends BaseController {

    @Autowired
    OrderService orderService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(value = "/createorder", method = {RequestMethod.POST},
            consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(
            @RequestParam(name = "itemId") Integer itemId,
            @RequestParam(name = "amount") Integer amount,
            @RequestParam(name = "promoId", required = false) Integer promoId
    ) throws BusinessException {
        // 获取用户登录信息
//        Boolean loggedIn = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGGED_IN");
//        if (loggedIn == null || !loggedIn.booleanValue())
//            throw new BusinessException(EmBusinessError.USER_NOT_LOGGED_IN);
//        UserModel user = (UserModel) httpServletRequest.getSession().getAttribute("LOGGED_IN_USER");
        String token = httpServletRequest.getParameterMap().get("token")[0];
        if (StringUtils.isBlank(token)) throw new BusinessException(EmBusinessError.USER_NOT_LOGGED_IN);
        UserModel user = (UserModel) redisTemplate.opsForValue().get(token);
        if (user == null) throw new BusinessException(EmBusinessError.USER_NOT_LOGGED_IN);
        OrderModel orderModel = orderService.createOrder(user.getId(), itemId, promoId, amount);

        return CommonReturnType.create(null);
    }
}
