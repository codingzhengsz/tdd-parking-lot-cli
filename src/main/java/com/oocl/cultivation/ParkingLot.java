package com.oocl.cultivation;

import com.oocl.exception.NotEnoughPositionException;
import com.oocl.exception.RepeatedParkingException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

  String name;
  int capacity;
  private Map<Ticket, Car> ticketCarMap;

  public ParkingLot(String name, int capacity) {
    this.name = name;
    this.capacity = capacity;
    this.ticketCarMap = new HashMap<>();
  }

  public Map<Ticket, Car> getTicketCarMap() {
    return ticketCarMap;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public Ticket park(Car car) throws RuntimeException {
    if (!isNotFull()) {
      throw new NotEnoughPositionException("Not enough position.");
    }
    if (this.ticketCarMap.containsValue(car)) {
      throw new RepeatedParkingException("Repeated Car");
    }
    Ticket ticket = new Ticket("", "", this.name);
    this.ticketCarMap.put(ticket, car);
    return ticket;
  }

  public Car fetch(Ticket ticket) {
    return ticketCarMap.getOrDefault(ticket, null);
  }

  public boolean isNotFull() {
    return this.ticketCarMap.size() != this.capacity;
  }

  public int getEmptyPositionNumber() {
    return this.capacity - this.ticketCarMap.size();
  }

  public double getAvailablePositionRate() {
    return 1.0d - ((double) this.ticketCarMap.size() / this.capacity);
  }
}
