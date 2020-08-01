package com.oocl.cultivation.exception;

public class NeedProvideCarException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Please provide your car.";
    }
}
