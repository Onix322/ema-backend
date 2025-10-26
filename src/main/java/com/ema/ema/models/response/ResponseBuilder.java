package com.ema.ema.models.response;

public class ResponseBuilder<T> {
    private Integer status;
    private String message;
    private T data;

    public ResponseBuilder() {
        this.status = 500;
        this.message = "Default message for response";
        this.data = null;
    }

    public Integer getStatus() {
        return status;
    }

    public ResponseBuilder<T> setStatus(Integer code) {
        this.status = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseBuilder<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseBuilder<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Response<T> build(){
        return new Response<>(this);
    }
}
