package com.oocl.cultivation;

import com.oocl.exception.NeedProvideParkingTicketException;
import com.oocl.exception.UnrecognizedPackingTicketException;

import java.util.List;

public abstract class AbstractParkingBoy {

  private List<ParkingLot> parkingLots;

  public Ticket parking(Car car) throws RuntimeException {
    return findWillBeParkedParkingLot().park(car);
  }

  public abstract ParkingLot findWillBeParkedParkingLot();

  public Car fetching(Ticket ticket) throws RuntimeException {
    if (null == ticket) {
      throw new NeedProvideParkingTicketException("Please provide your parking ticket.");
    }
    Car car = this.parkingLots.stream().map(lot -> lot.fetch(ticket)).findFirst().get();
    if (null == car) {
      throw new UnrecognizedPackingTicketException("Unrecognized parking ticket.");
    }
    return car;
  }

  public List<ParkingLot> getParkingLots() {
    return parkingLots;
  }

  public void setParkingLots(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }
}
