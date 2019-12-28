package com.scaffold.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ErrorResponse {

    @JsonProperty("errors")
    private List<Error> errors = new ArrayList<>();

    public ErrorResponse add(Error error){
        errors.add(error);
        return this;
    }

    public ErrorResponse addAll(List<Error> errors){
        this.errors.addAll(errors);
        return this;
    }

}
