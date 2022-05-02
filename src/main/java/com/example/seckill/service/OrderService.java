package com.example.seckill.service;

import com.example.seckill.error.BusinessException;
import com.example.seckill.service.model.OrderModel;

public interface OrderService {
    OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException;

}
