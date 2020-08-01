package com.oocl.cultivation.exception;

public class RepeatedParkingException extends RuntimeException {
  @Override
  public String getMessage() {
    return "This car is parked";
  }
}
