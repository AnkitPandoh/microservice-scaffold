package com.scaffold.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Response<T> {

    @JsonProperty
    private T result;

    @JsonProperty
    private int code;

    public Response(T result, int code){
        this.result = result;
        this.code = code;
    }
}
