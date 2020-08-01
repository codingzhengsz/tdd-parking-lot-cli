package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;

public class StandardParkingBoy extends AbstractParkingBoy {

  @Override
  public ParkingLot findWillBeParkedParkingLot() {
    return this.getParkingLots().stream()
        .filter(parkingLot -> !parkingLot.isFull())
        .findFirst()
        .orElseThrow(NotEnoughPositionException::new);
  }
}
