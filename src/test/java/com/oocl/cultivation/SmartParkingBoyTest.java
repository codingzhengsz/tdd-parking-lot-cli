package com.oocl.cultivation;

import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.entity.Ticket;
import com.oocl.cultivation.exception.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

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

  @Test
  void should_return_ticket_when_park_given_a_car_and_a_smart_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 10);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
    smartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car car = new Car();

    // when
    Ticket ticket = smartParkingBoy.parking(car);

    // then
    assertNotNull(ticket);
  }

  @Test
  void should_return_tickets_when_park_given_2_car_and_a_smart_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 10);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
    smartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car carA = new Car();
    Car carB = new Car();

    // when
    Ticket ticketA = smartParkingBoy.parking(carA);
    Ticket ticketB = smartParkingBoy.parking(carB);

    // then
    assertNotNull(ticketA);
    assertNotNull(ticketB);
    assertNotEquals(ticketA, ticketB);
  }

  @Test
  void should_return_a_car_when_fetch_given_a_ticket_and_a_smart_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 10);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
    smartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car car = new Car();
    Ticket ticket = smartParkingBoy.parking(car);

    // when
    Car fetchedCar = smartParkingBoy.fetching(ticket);

    // then
    assertNotNull(fetchedCar);
  }

  @Test
  void
      should_throw_unrecognized_parking_ticket_exception_when_fetch_given_a_wrong_ticket_and_a_smart_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 10);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
    smartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car car = new Car();
    smartParkingBoy.parking(car);
    Ticket ticket = new Ticket();

    // when
    UnrecognizedPackingTicketException exception =
        assertThrows(
            UnrecognizedPackingTicketException.class, () -> smartParkingBoy.fetching(ticket));

    // then
    assertEquals("Unrecognized parking ticket.", exception.getMessage());
  }

  @Test
  void
      should_throw_unrecognized_parking_ticket_exception_when_fetch_given_a_used_ticket_and_a_smart_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 10);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
    smartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car car = new Car();
    Ticket ticket = smartParkingBoy.parking(car);

    // when
    smartParkingBoy.fetching(ticket);
    UnrecognizedPackingTicketException exception =
        assertThrows(
            UnrecognizedPackingTicketException.class, () -> smartParkingBoy.fetching(ticket));

    // then
    assertEquals("Unrecognized parking ticket.", exception.getMessage());
  }

  @Test
  void should_throw_parked_car_exception_when_park_given_a_parked_car_and_a_smart_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 10);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
    smartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car parkedCar = new Car();
    smartParkingBoy.parking(parkedCar);

    // when
    RepeatedParkingException exception =
        assertThrows(RepeatedParkingException.class, () -> smartParkingBoy.parking(parkedCar));

    // then
    assertEquals("This car is parked", exception.getMessage());
  }

  @Test
  void should_throw_need_car_exception_when_park_given_a_null_car_and_a_smart_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 10);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
    smartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));

    // when
    NeedProvideCarException exception =
        assertThrows(NeedProvideCarException.class, () -> smartParkingBoy.parking(null));

    // then
    assertEquals("Please provide your car.", exception.getMessage());
  }

  @Test
  void
      should_throw_need_provide_ticket_exception_when_fetch_given_a_null_ticket_and_a_smart_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("1", 10);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
    smartParkingBoy.setParkingLots(Collections.singletonList(parkingLot));

    // when
    Throwable exception =
        assertThrows(NeedProvideParkingTicketException.class, () -> smartParkingBoy.fetching(null));

    // then
    assertEquals("Please provide your parking ticket.", exception.getMessage());
  }

  @Test
  void should_get_ticket_when_park_given_1_car_and_2_parking_lots_and_a_smart_parking_boy() {
    // given
    ParkingLot parkingLot1 = new ParkingLot("1", 1);
    ParkingLot parkingLot2 = new ParkingLot("2", 1);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
    smartParkingBoy.setParkingLots(Arrays.asList(parkingLot1, parkingLot2));
    Car car = new Car();

    // when
    Ticket ticket = smartParkingBoy.parking(car);

    // then
    assertNotNull(ticket);
    assertTrue(parkingLot1.isFull());
    assertFalse(parkingLot2.isFull());
  }
}
