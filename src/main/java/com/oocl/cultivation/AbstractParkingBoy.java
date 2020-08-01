package com.oocl.cultivation;

import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.entity.Ticket;
import com.oocl.cultivation.exception.NeedProvideCarException;
import com.oocl.cultivation.exception.NeedProvideParkingTicketException;
import com.oocl.cultivation.exception.RepeatedParkingException;
import com.oocl.cultivation.exception.UnrecognizedPackingTicketException;

import java.security.UnrecoverableEntryException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractParkingBoy {

  private List<ParkingLot> parkingLots;

  public Ticket parking(Car car) throws RuntimeException {
    if (null == car) {
      throw new NeedProvideCarException();
    }
    if (this.parkingLots.stream().findAny().filter(lot -> lot.hasCar(car)).isPresent()) {
      throw new RepeatedParkingException();
    }
    return findWillBeParkedParkingLot().park(car);
  }

  public abstract ParkingLot findWillBeParkedParkingLot();

  public Car fetching(Ticket ticket) throws RuntimeException {
    if (null == ticket) {
      throw new NeedProvideParkingTicketException();
    }
    return this.parkingLots.stream()
        .findAny()
        .map(parkingLot -> parkingLot.fetch(ticket))
        .orElseThrow(UnrecognizedPackingTicketException::new);
  }

  public List<ParkingLot> getParkingLots() {
    return parkingLots;
  }

  public void setParkingLots(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }
}
