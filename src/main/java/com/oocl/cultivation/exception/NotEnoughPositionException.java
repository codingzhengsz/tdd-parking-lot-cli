package com.oocl.cultivation.exception;

public class NotEnoughPositionException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Not enough position.";
    }
}
