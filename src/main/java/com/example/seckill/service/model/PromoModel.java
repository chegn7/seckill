package com.example.seckill.service.model;

import lombok.Data;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * 秒杀活动模型
 * @author cheng
 * @date 2022-05-02 16:41:39
 */
@Data
public class PromoModel {
    private Integer id;
    // 状态 1 未开始 2 正在进行 3 已结束
    private Integer status;
    private String promoName;
    private DateTime startDate;
    private DateTime endDate;
    private Integer itemId;
    private BigDecimal promoItemPrice;
}
