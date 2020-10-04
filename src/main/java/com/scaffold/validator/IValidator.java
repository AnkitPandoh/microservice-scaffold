package com.scaffold.validator;

@FunctionalInterface
public interface IValidator<T> {

    ValidationResult validate(T t);

    default IValidator<T> and(IValidator<T> other) {
        return t -> {
            ValidationResult result = this.validate(t);
            return !result.isValid() ? result : other.validate(t);
        };
    }

    default IValidator<T> or(IValidator<T> other) {
        return t -> {
            ValidationResult result = this.validate(t);
            return result.isValid() ? other.validate(t) : result;
        };
    }

    static <T> ValidationResult allMatch(T t, IValidator<T>... other){
        ValidationResult result = null;
        for(IValidator o : other){
            result = o.validate(t);
            if(!result.isValid()){
                return result;
            }
        }
        return result;
    }
}
