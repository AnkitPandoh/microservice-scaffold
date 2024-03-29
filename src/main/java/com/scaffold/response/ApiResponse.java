package com.scaffold.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiResponse<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T response;

    private boolean ok;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    public ApiResponse(T response) {
        this.response = response;
        this.ok = true;
        this.error = null;
    }

    public ApiResponse(String error) {
        this.response = null;
        this.ok = false;
        this.error = error;
    }

    public ApiResponse() {
        this.ok = false;
        this.response = null;
        this.error = null;
    }

    public T getResponse() {
        return response;
    }

    public boolean isOk() {
        return ok;
    }

    public String getError() {
        return error;
    }
}
