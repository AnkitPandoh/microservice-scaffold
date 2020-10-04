package com.scaffold.validator;

import com.scaffold.model.Error;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestValidator {

    @Test
    public void shouldReturnTrue_whenStringIsNotEmpty_and_hasMinLength() {
        String str = "hello";
        int minLength = 3;
        ValidationResult result = ValidationRules.notEmpty(new Error(1, "String is Empty"))
                .and(ValidationRules.hasMinLength(minLength, new Error(2, "Violates min length rule")))
                .validate(str);
        Assertions.assertTrue(result.isValid());
    }

    @Test
    public void shouldReturnFalse_whenStringIsNotEmpty_and_hasExceedLength() {
        String str = "he";
        int minLength = 3;
        ValidationResult result = ValidationRules.notEmpty(new Error(1, "String is Empty"))
                .and(ValidationRules.hasMinLength(minLength, new Error(2, "Violates min length rule")))
                .validate(str);
        Assertions.assertFalse(result.isValid());
    }
}
