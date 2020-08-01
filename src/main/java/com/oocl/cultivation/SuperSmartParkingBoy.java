package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;

import java.util.Comparator;

public class SuperSmartParkingBoy extends AbstractParkingBoy {
  @Override
  public ParkingLot findWillBeParkedParkingLot() {
    return this.getParkingLots().stream()
        .max(Comparator.comparing(ParkingLot::getAvailablePositionRate))
        .filter(lot -> !lot.isFull())
        .orElseThrow(NotEnoughPositionException::new);
  }
}
