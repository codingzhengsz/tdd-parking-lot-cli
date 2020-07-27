package com.oocl.cultivation;

import java.util.Comparator;

public class SmartParkingBoy extends AbstractParkingBoy {
  @Override
  public ParkingLot findWillBeParkedParkingLot() {
    return this.getParkingLots().stream()
        .max(Comparator.comparing(ParkingLot::getEmptyPositionNumber))
        .get();
  }
}
