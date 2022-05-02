package com.example.seckill.error;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EmBusinessError implements CommonError {
    // 1xxxx 为通用错误类型
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),

    UNKNOWN_ERROR(10002, "未知错误"),
    // 2xxxx 为用户信息相关错误定义
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_LOGIN_FAILED(20002, "用户名或密码错误"),
    USER_NOT_LOGGED_IN(20003, "用户尚未登陆"),

    // 3xxxx 商品相关错误
    ITEM_NOT_EXIST(30001, "商品不存在"),

    // 4xxxx 交易相关错误
    STOCK_NOT_ENOUGH(40001, "商品库存不足"),
    SALES_NOT_INCREASED(40002, "商品销量更新失败");

    private int errorCode;
    private String errorMessage;


    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public CommonError setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
