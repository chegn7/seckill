package com.example.seckill.controller.viewobject;

import lombok.Data;

/**
 * @author cheng
 * @date 2022-04-30 11:36:13
 */
@Data
public class UserVO {
    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
    private String telephone;
}
