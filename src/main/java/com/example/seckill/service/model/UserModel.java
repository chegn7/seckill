package com.example.seckill.service.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cheng
 * @date 2022-04-29 22:33:04
 */
@Data
public class UserModel {
    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
    private String telephone;
    private String registerMode;
    private String thirdPartyId;

    private String encryptPassword;

}
