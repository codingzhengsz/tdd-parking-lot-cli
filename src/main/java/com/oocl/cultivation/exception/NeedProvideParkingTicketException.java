package com.oocl.cultivation.exception;

public class NeedProvideParkingTicketException extends RuntimeException {
  @Override
  public String getMessage() {
    return "Please provide your parking ticket.";
  }
}
