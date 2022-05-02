package com.example.seckill.dataobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author cheng
 * @date 2022-05-02 16:53:10
 */
@Data
public class PromoDO {
    private Integer id;
    private String promoName;
    private Date startDate;
    private Integer itemId;
    private BigDecimal promoItemPrice;
    private Date endDate;
}
