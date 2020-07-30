package com.oocl.cultivation;

public class StandardParkingBoy extends AbstractParkingBoy {

  @Override
  public ParkingLot findWillBeParkedParkingLot() {
    return this.getParkingLots().stream().filter(ParkingLot::isNotFull).findFirst().get();
  }
}
