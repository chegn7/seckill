package com.example.seckill.service.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author cheng
 * @date 2022-05-01 16:47:11
 */
@Data
public class ItemModel {
    private Integer id;
    // 商品标题/名称
    @NotBlank(message = "商品名不能为空")
    private String title;
    // 商品价格
    @NotNull(message = "商品价格不能为空")
    @Min(value = 0, message = "价格必须为正数")
    private BigDecimal price;
    // 商品库存
    @NotNull(message = "库存必填")
    private Integer stock;
    // 商品描述
    @NotBlank(message = "描述不能为空")
    private String description;
    // 商品销量
    private Integer sales;
    // 商品图片
    @NotBlank(message = "图片不能为空")
    private String imgUrl;

}
