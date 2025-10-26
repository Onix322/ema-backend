package com.ema.ema.exceptions;

public class CannotBeModified extends RuntimeException {
    public CannotBeModified(String message) {
        super(message);
    }
}
