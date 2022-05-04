package com.example.seckill.controller;

/**
 * @author cheng
 * @date 2022-04-30 13:29:06
 */
public class BaseController {

    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public Object handlerException(HttpServletRequest request, Exception exception) {
//        Map<String, Object> responseData = new HashMap<>();
//        if (exception instanceof BusinessException) {
//            BusinessException businessException = (BusinessException) exception;
//
//            responseData.put("errorCode", businessException.getErrorCode());
//            responseData.put("errorMessage", businessException.getErrorMessage());
//        } else {
//            responseData.put("errorCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
//            responseData.put("errorMessage", EmBusinessError.UNKNOWN_ERROR.getErrorMessage());
//        }
//        return CommonReturnType.create(responseData, "fail");
//    }
}
