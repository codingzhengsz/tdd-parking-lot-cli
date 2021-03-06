package com.oocl.cultivation;

import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.entity.Ticket;

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

  public Ticket park(Car car) throws RuntimeException {
    Ticket ticket = new Ticket();
    this.ticketCarMap.put(ticket, car);
    return ticket;
  }

  public Car fetch(Ticket ticket) {
    Car fetchedCar = ticketCarMap.getOrDefault(ticket, null);
    ticketCarMap.remove(ticket);
    return fetchedCar;
  }

  public boolean isFull() {
    return this.ticketCarMap.size() >= this.capacity;
  }

  public boolean hasCar(Car car) {
    return this.ticketCarMap.containsValue(car);
  }

  public int getEmptyPositionNumber() {
    return this.capacity - this.ticketCarMap.size();
  }

  public double getAvailablePositionRate() {
    return 1.0d - ((double) this.ticketCarMap.size() / this.capacity);
  }
}
