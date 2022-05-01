package com.example.seckill.validator;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cheng
 * @date 2022-05-01 10:13:43
 */
@Data
public class ValidationResult {
    // 校验结果
    private boolean hasErrors;
    // 错误信息的Map
    private Map<String, String> errorMessageMap;

    public ValidationResult() {
        hasErrors = false;
        errorMessageMap = new HashMap<>();
    }

    // 通过格式化字符串信息获取错误结果的message方法
    public String getErrorMessage() {
        return StringUtils.join(errorMessageMap.values(),",");
    }
}
