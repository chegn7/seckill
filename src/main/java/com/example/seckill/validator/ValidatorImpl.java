package com.example.seckill.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;
import java.util.Set;

/**
 * @author cheng
 * @date 2022-05-01 10:19:33
 */
@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    @Override
    public void afterPropertiesSet() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public ValidationResult validate(Object bean) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
        if (constraintViolationSet.size() > 0) {
            result.setHasErrors(true);
            Map<String, String> errorMessageMap = result.getErrorMessageMap();
            for (ConstraintViolation<Object> objectConstraintViolation : constraintViolationSet) {
                String errorMessage = objectConstraintViolation.getMessage();
                String propertyName = objectConstraintViolation.getPropertyPath().toString();
                errorMessageMap.put(propertyName, errorMessage);
            }
        }
        return result;
    }
}
