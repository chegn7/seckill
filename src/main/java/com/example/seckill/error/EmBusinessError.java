package com.example.seckill.error;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EmBusinessError implements CommonError {
    // 1xxxx 为通用错误类型
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),

    UNKNOWN_ERROR(10002, "未知错误"),
    // 2xxxx 为用户信息相关错误定义
    USER_NOT_EXIST(20001, "用户不存在");

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
