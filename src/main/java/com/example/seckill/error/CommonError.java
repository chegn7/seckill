package com.example.seckill.error;

public interface CommonError {
    int getErrorCode();
    String getErrorMessage();
    public CommonError setErrorMessage(String errorMessage);
}
