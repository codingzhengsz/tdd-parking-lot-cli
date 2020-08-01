package com.oocl.cultivation;

import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.entity.Ticket;
import com.oocl.cultivation.exception.NeedProvideCarException;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.RepeatedParkingException;
import com.oocl.cultivation.exception.UnrecognizedPackingTicketException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

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
  void
      should_park_2_cars_into_lot1_and_6_into_lot2_when_parking_given_a_super_smart_parking_boy_and_8_cars_and_2_lots() {
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

  @Test
  void should_return_ticket_when_park_given_a_car_and_a_super_smart_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 10);
    SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
    superSmartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car car = new Car();

    // when
    Ticket ticket = superSmartParkingBoy.parking(car);

    // then
    assertNotNull(ticket);
  }

  @Test
  void should_return_tickets_when_park_given_2_car_and_a_super_smart_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 10);
    SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
    superSmartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car carA = new Car();
    Car carB = new Car();

    // when
    Ticket ticketA = superSmartParkingBoy.parking(carA);
    Ticket ticketB = superSmartParkingBoy.parking(carB);

    // then
    assertNotNull(ticketA);
    assertNotNull(ticketB);
    assertNotEquals(ticketA, ticketB);
  }

  @Test
  void should_return_a_car_when_fetch_given_a_ticket_and_a_super_smart_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 10);
    SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
    superSmartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car car = new Car();
    Ticket ticket = superSmartParkingBoy.parking(car);

    // when
    Car fetchedCar = superSmartParkingBoy.fetching(ticket);

    // then
    assertNotNull(fetchedCar);
  }

  @Test
  void
      should_throw_unrecognized_parking_ticket_exception_when_fetch_given_a_wrong_ticket_and_a_super_smart_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 10);
    SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
    superSmartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car car = new Car();
    superSmartParkingBoy.parking(car);
    Ticket ticket = new Ticket();

    // when
    UnrecognizedPackingTicketException exception =
        assertThrows(
            UnrecognizedPackingTicketException.class, () -> superSmartParkingBoy.fetching(ticket));

    // then
    assertEquals("Unrecognized parking ticket.", exception.getMessage());
  }

  @Test
  void
      should_throw_unrecognized_parking_ticket_exception_when_fetch_given_a_used_ticket_and_a_super_smart_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 10);
    SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
    superSmartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car car = new Car();
    Ticket ticket = superSmartParkingBoy.parking(car);

    // when
    superSmartParkingBoy.fetching(ticket);
    UnrecognizedPackingTicketException exception =
        assertThrows(
            UnrecognizedPackingTicketException.class, () -> superSmartParkingBoy.fetching(ticket));

    // then
    assertEquals("Unrecognized parking ticket.", exception.getMessage());
  }

  @Test
  void
      should_throw_parked_car_exception_when_park_given_a_parked_car_and_a_super_smart_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 10);
    SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
    superSmartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car parkedCar = new Car();
    superSmartParkingBoy.parking(parkedCar);

    // when
    RepeatedParkingException exception =
        assertThrows(RepeatedParkingException.class, () -> superSmartParkingBoy.parking(parkedCar));

    // then
    assertEquals("This car is parked", exception.getMessage());
  }

  @Test
  void should_throw_need_car_exception_when_park_given_a_null_car_and_a_super_smart_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 10);
    SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
    superSmartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));

    // when
    NeedProvideCarException exception =
        assertThrows(NeedProvideCarException.class, () -> superSmartParkingBoy.parking(null));

    // then
    assertEquals("Please provide your car.", exception.getMessage());
  }


}
