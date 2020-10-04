package com.scaffold.validator;

import com.scaffold.model.Error;

import java.util.function.Predicate;

public class Validator<T> implements IValidator<T> {

    private final Predicate<T> predicate;
    private final Error error;

    private Validator(Predicate<T> predicate, Error error) {
        this.predicate = predicate;
        this.error = error;
    }

    public static <T> Validator evaluate(Predicate<T> predicate, Error error) {
        return new Validator(predicate, error);
    }

    @Override
    public ValidationResult validate(T t) {
        return this.predicate.test(t) ? ValidationResult.pass() : ValidationResult.fail(this.error);
    }
}
