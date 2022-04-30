package com.example.seckill.error;

/**
 * 包装器业务异常类实现
 * @author cheng
 * @date 2022-04-30 12:29:09
 */
public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;

    // 直接接收 Error 构造业务异常
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    // 接收自定义 errorMessage 的方式构造业务异常
    public BusinessException(CommonError commonError, String errorMessage) {
        this(commonError);
        this.commonError.setErrorMessage(errorMessage);
    }

    @Override
    public int getErrorCode() {
        return commonError.getErrorCode();
    }

    @Override
    public String getErrorMessage() {
        return commonError.getErrorMessage();
    }

    @Override
    public CommonError setErrorMessage(String errorMessage) {
        commonError.setErrorMessage(errorMessage);
        return this;
    }
}
