package com.example.seckill.controller.viewobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author cheng
 * @date 2022-05-01 18:29:08
 */
@Data
public class ItemVO {
    private Integer id;
    // 商品标题/名称
    private String title;
    // 商品价格
    private BigDecimal price;
    // 商品库存
    private Integer stock;
    // 商品描述
    private String description;
    // 商品销量
    private Integer sales;
    // 商品图片
    private String imgUrl;

    // 商品秒杀状态  0 无秒杀活动 1 即将开始 2 正在进行
    private Integer promoStatus;
    private Integer promoId;
    private String startDate;
    private BigDecimal promoPrice;
}
