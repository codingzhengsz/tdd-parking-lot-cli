package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;

public class StandardParkingBoy extends AbstractParkingBoy {

  @Override
  public ParkingLot findWillBeParkedParkingLot() {
    return this.getParkingLots().stream()
        .findFirst()
        .filter(parkingLot -> !parkingLot.isFull())
        .orElseThrow(NotEnoughPositionException::new);
  }
}
