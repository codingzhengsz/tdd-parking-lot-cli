package com.oocl.cultivation.test;

import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.SmartParkingBoy;
import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

  @Test
  void
      should_throw_not_enough_position_exception_when_parking_given_a_smart_parking_boy_and_a_parking_lot() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 0);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
    smartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));

    // when
    NotEnoughPositionException exception =
        assertThrows(NotEnoughPositionException.class, () -> smartParkingBoy.parking(new Car()));

    // then
    assertEquals("Not enough position.", exception.getMessage());
  }
}
