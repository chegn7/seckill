package com.example.seckill.service.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author cheng
 * @date 2022-05-01 21:09:46
 */
@Data
public class OrderModel {
    // 交易号有意义
    private String id;
    private Integer userId;
    private Integer itemId;
    // 购买商品时的商品价格
    private BigDecimal itemPrice;
    // 交易数量
    private Integer amount;
    // 交易金额
    private BigDecimal orderPrice;
}
