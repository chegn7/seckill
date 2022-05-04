package com.example.seckill.controller;

import com.example.seckill.error.BusinessException;
import com.example.seckill.error.EmBusinessError;
import com.example.seckill.response.CommonReturnType;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cheng
 * @date 2022-05-03 21:32:09
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonReturnType doError(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception exception) {
        exception.printStackTrace();
        Map<String , Object> responseData = new HashMap<>();
        if (exception instanceof BusinessException) {
            BusinessException businessException = (BusinessException) exception;
            responseData.put("errorCode", businessException.getErrorCode());
            responseData.put("errorMessage", businessException.getErrorMessage());
        } else if (exception instanceof ServletRequestBindingException) {
            responseData.put("errorCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errorMessage", "url绑定路由问题");
        } else if (exception instanceof NoHandlerFoundException) {
            responseData.put("errorCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errorMessage", "未找到访问路径");
        } else {
            responseData.put("errorCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errorMessage", EmBusinessError.UNKNOWN_ERROR.getErrorMessage());
        }
        return CommonReturnType.create(responseData, "fail");
    }
}
