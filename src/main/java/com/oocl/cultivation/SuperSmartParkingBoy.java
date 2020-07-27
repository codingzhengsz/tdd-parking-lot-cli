package com.oocl.cultivation;

import java.util.Comparator;

public class SuperSmartParkingBoy extends AbstractParkingBoy {
  @Override
  public ParkingLot findWillBeParkedParkingLot() {
    return this.getParkingLots().stream()
        .max(Comparator.comparing(ParkingLot::getAvailablePositionRate))
        .get();
  }
}
