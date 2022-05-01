package com.example.seckill.validator;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ValidationResultTest {

    @Test
    void getErrorMessage() {
        ValidationResult result = new ValidationResult();
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        result.setErrorMessageMap(map);
        System.out.println(result.getErrorMessage());

    }
}