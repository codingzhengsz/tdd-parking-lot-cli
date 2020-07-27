package com.oocl.exception;

public class NotEnoughPositionException extends RuntimeException {
    public NotEnoughPositionException(String message) {
        super(message);
    }
}
