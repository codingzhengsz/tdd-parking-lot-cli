package com.oocl.cultivation.test;

import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.SmartParkingBoy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SmartParkingBoyTest {

  @Test
  void should_get_lot_2_when_find_park_lot_given_a_smart_parking_boy_and_two_parking_lot() {
    // given
    ParkingLot parkingLot1 = new ParkingLot("1", 5);
    ParkingLot parkingLot2 = new ParkingLot("2", 10);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
    smartParkingBoy.setParkingLots(Arrays.asList(parkingLot1, parkingLot2));

    // when
    ParkingLot parkingLot = smartParkingBoy.findWillBeParkedParkingLot();

    // then
    assertEquals(10, parkingLot.getEmptyPositionNumber());
  }
}
