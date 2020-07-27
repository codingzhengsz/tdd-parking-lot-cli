package com.oocl.exception;

public class NeedProvideParkingTicketException extends RuntimeException {
    public NeedProvideParkingTicketException(String message) {
        super(message);
    }
}
