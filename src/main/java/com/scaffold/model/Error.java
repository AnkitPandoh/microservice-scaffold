package com.scaffold.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {

    @JsonProperty
    private int code;

    @JsonProperty
    private String message;

    public Error(final int code, final String message){
        this.code = code;
        this.message = message;
    }
}
