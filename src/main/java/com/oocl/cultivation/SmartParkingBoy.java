package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;

import java.util.Comparator;

public class SmartParkingBoy extends AbstractParkingBoy {
  @Override
  public ParkingLot findWillBeParkedParkingLot() {
    return this.getParkingLots().stream()
        .max(Comparator.comparing(ParkingLot::getEmptyPositionNumber))
        .filter(lot -> !lot.isFull())
        .orElseThrow(NotEnoughPositionException::new);
  }
}
