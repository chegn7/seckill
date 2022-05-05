package com.example.seckill.service.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cheng
 * @date 2022-04-29 22:33:04
 */
@Data
public class UserModel implements Serializable {

    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String name;
    @NotNull(message = "性别必须填写")
    private Byte gender;
    @NotNull(message = "年龄必须填写")
    @Min(value = 0,message = "年龄不能为负")
    @Max(value = 200,message = "年龄不能大于200")
    private Integer age;
    @NotBlank(message = "手机号不能为空")
    private String telephone;
    private String registerMode;
    private String thirdPartyId;
    @NotBlank(message = "密码不能为空")
    private String encryptPassword;

}
