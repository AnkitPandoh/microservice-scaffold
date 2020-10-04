package com.scaffold.validator;

import com.scaffold.model.Error;
import org.springframework.util.StringUtils;

public class ValidationRules {

    private ValidationRules() {
    }

    public static IValidator<String> notEmpty(Error error) {
        return Validator.evaluate(str -> !StringUtils.isEmpty(str), error);
    }

    public static IValidator<String> hasMinLength(int minLength, Error error) {
        return Validator.evaluate(str -> ((String) str).length() >= minLength, error);
    }
}
