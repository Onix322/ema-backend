package com.ema.ema.handler.responseHandler;

import com.ema.ema.models.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static <T> ResponseEntity<Response<T>> created(T data) {
        Response<T> response = Response.<T>builder()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Entity Created Successfully!")
                .setData(data)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    public static <T> ResponseEntity<Response<T>> ok(T data) {
        Response<T> response = Response.<T>builder()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Success!")
                .setData(data)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    public static ResponseEntity<Response<Object>> fail(Integer code, String message) {
        Response<Object> response = Response.builder()
                .setStatus(code)
                .setMessage(message)
                .setData(null)
                .build();
        return ResponseEntity
                .status(code)
                .body(response);
    }
}
