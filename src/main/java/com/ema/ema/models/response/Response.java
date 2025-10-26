package com.ema.ema.models.response;

import java.util.Objects;

public class Response<T> {
    private Integer code;
    private String message;
    private T data;

    protected Response(ResponseBuilder<T> responseBuilder) {
        this.code = responseBuilder.getStatus();
        this.message = responseBuilder.getMessage();
        this.data = responseBuilder.getData();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Response<?> response = (Response<?>) o;
        return Objects.equals(getCode(), response.getCode()) && Objects.equals(getMessage(), response.getMessage()) && Objects.equals(getData(), response.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getMessage(), getData());
    }

    public static <T> ResponseBuilder<T> builder() {
        return new ResponseBuilder<T>();
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
