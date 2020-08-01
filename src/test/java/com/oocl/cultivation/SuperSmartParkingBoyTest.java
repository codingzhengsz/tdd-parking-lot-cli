package com.oocl.cultivation;

import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuperSmartParkingBoyTest {

  @Test
  void
      should_throw_not_enough_position_exception_when_parking_given_a_super_smart_parking_boy_and_a_parking_lot() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 0);
    SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
    superSmartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));

    // when
    NotEnoughPositionException exception =
        assertThrows(
            NotEnoughPositionException.class, () -> superSmartParkingBoy.parking(new Car()));

    // then
    assertEquals("Not enough position.", exception.getMessage());
  }

  @Test
  void should_get_parking_lot1_when_parking_given_a_super_smart_parking_boy_and_a_parking_boy() {
    // given
    ParkingLot parkingLot1 = new ParkingLot("1", 10);
    ParkingLot parkingLot2 = new ParkingLot("2", 50);
    SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
    superSmartParkingBoy.setParkingLots(Arrays.asList(parkingLot1, parkingLot2));

    // when
    for (int i = 0; i < 8; i++) {
      superSmartParkingBoy.parking(new Car());
    }

    // then
    assertEquals(8, parkingLot1.getEmptyPositionNumber());
    assertEquals(44, parkingLot2.getEmptyPositionNumber());
  }
}
