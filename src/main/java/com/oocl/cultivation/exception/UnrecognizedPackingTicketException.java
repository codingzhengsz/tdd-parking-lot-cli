package com.oocl.cultivation.exception;

public class UnrecognizedPackingTicketException extends RuntimeException {

  @Override
  public String getMessage() {
    return "Unrecognized parking ticket.";
  }
}
