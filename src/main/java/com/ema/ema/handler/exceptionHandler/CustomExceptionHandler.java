package com.ema.ema.handler.exceptionHandler;

import com.ema.ema.exceptions.CannotBeCreated;
import com.ema.ema.exceptions.CannotBeModified;
import com.ema.ema.exceptions.NotFoundException;
import com.ema.ema.handler.responseHandler.ResponseHandler;
import com.ema.ema.models.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CannotBeCreated.class)
    public ResponseEntity<Response<Object>> notCreated(CannotBeCreated e) {
        return ResponseHandler.fail(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(CannotBeModified.class)
    public ResponseEntity<Response<Object>> notModified(CannotBeModified e) {
        return ResponseHandler.fail(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response<Object>> notFound() {
        return ResponseHandler.fail(HttpStatus.NOT_FOUND.value(), "Entity not found!");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response<Object>> internal() {
        return ResponseHandler.fail(500, "Internal exception!");
    }
}
