package com.example.seckill.dataobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author cheng
 * @date 2022-05-01 23:06:10
 */
@Data
public class OrderDO {
    String id;
    Integer userId;
    Integer itemId;
    BigDecimal itemPrice;
    Integer amount;
    BigDecimal orderPrice;
}
