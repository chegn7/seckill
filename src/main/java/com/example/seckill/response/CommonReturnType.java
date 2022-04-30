package com.example.seckill.response;

import lombok.Data;

/**
 * 通用的返回类，返回给前端
 * @author cheng
 * @date 2022-04-30 12:02:11
 */
@Data
public class CommonReturnType {
    // "success" / "fail"
    private String status;
    // status == success data 返回前端需要的JSON
    // status == fail data 使用通用的错误码格式
    private Object data;

    // 定义一个通用的创建方法
    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result, "success");
    }

    public static CommonReturnType create(Object result, String status) {
        CommonReturnType returnType = new CommonReturnType();
        returnType.setStatus(status);
        returnType.setData(result);
        return returnType;
    }
}
