package com.scaffold.validator;

import com.scaffold.model.Error;
import lombok.Getter;

import java.util.Optional;

@Getter
public class ValidationResult {
    private boolean valid;
    private Optional<Error> error;

    private ValidationResult(boolean valid, Optional<Error> error){
        this.valid = valid;
        this.error = error;
    }

    public static ValidationResult pass(){
        return new ValidationResult(true, Optional.empty());
    }

    public static ValidationResult fail(Error error){
        return new ValidationResult(false, Optional.of(error));
    }
}
