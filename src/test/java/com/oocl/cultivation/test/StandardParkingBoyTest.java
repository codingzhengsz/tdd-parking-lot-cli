package com.oocl.cultivation.test;

import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.StandardParkingBoy;
import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.entity.Ticket;
import com.oocl.cultivation.exception.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class StandardParkingBoyTest {
  @Test
  void should_return_ticket_when_park_given_a_car_and_a_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("ParkingLot_1", 10);
    StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
    standardParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car car = new Car();

    // when
    Ticket ticket = standardParkingBoy.parking(car);

    // then
    assertNotNull(ticket);
  }

  @Test
  void should_return_tickets_when_park_given_2_car_and_a_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("ParkingLot_1", 10);
    StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
    standardParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car carA = new Car();
    Car carB = new Car();

    // when
    Ticket ticketA = standardParkingBoy.parking(carA);
    Ticket ticketB = standardParkingBoy.parking(carB);

    // then
    assertNotNull(ticketA);
    assertNotNull(ticketB);
    assertNotEquals(ticketA, ticketB);
  }

  @Test
  void should_return_a_car_when_fetch_given_a_ticket_and_a_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("ParkingLot_1", 10);
    StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
    standardParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car car = new Car();
    Ticket ticket = standardParkingBoy.parking(car);

    // when
    Car fetchedCar = standardParkingBoy.fetching(ticket);

    // then
    assertNotNull(fetchedCar);
  }

  @Test
  void should_return_no_car_when_fetch_given_a_wrong_ticket_and_a_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("ParkingLot_1", 10);
    StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
    standardParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car car = new Car();
    standardParkingBoy.parking(car);
    Ticket ticket = new Ticket();

    // when
    UnrecognizedPackingTicketException exception =
        assertThrows(
            UnrecognizedPackingTicketException.class, () -> standardParkingBoy.fetching(ticket));

    // then
    assertEquals("Unrecognized parking ticket.", exception.getMessage());
  }

  @Test
  void should_return_no_car_when_fetch_given_a_used_ticket_and_a_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("ParkingLot_1", 10);
    StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
    standardParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car car = new Car();
    Ticket ticket = standardParkingBoy.parking(car);

    // when
    standardParkingBoy.fetching(ticket);
    UnrecognizedPackingTicketException exception =
        assertThrows(
            UnrecognizedPackingTicketException.class, () -> standardParkingBoy.fetching(ticket));

    // then
    assertEquals("Unrecognized parking ticket.", exception.getMessage());
  }

  @Test
  void
      should_get_no_ticket_when_park_given_one_cars_and_a_parking_boy_and_a_parking_lot_without_position() {
    // given
    ParkingLot parkingLot = new ParkingLot("ParkingLot_1", 0);
    StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
    standardParkingBoy.setParkingLots(Collections.singletonList(parkingLot));

    // when
    NotEnoughPositionException exception =
        assertThrows(NotEnoughPositionException.class, () -> standardParkingBoy.parking(new Car()));

    // then
    assertEquals("Not enough position.", exception.getMessage());
  }

  @Test
  void should_get_no_ticket_when_park_given_a_parked_car_and_a_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("ParkingLot_1", 10);
    StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
    standardParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
    Car parkedCar = new Car();
    standardParkingBoy.parking(parkedCar);

    // when
    RepeatedParkingException exception =
        assertThrows(RepeatedParkingException.class, () -> standardParkingBoy.parking(parkedCar));

    // then
    assertEquals("This car is parked", exception.getMessage());
  }

  @Test
  void should_throw_need_car_exception_when_park_given_a_null_car_and_a_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("ParkingLot_1", 10);
    StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
    standardParkingBoy.setParkingLots(Collections.singletonList(parkingLot));

    // when
    NeedProvideCarException exception =
        assertThrows(NeedProvideCarException.class, () -> standardParkingBoy.parking(null));

    // then
    assertEquals("Please provide your car.", exception.getMessage());
  }

  @Test
  void should_get_need_provide_ticket_msg_when_fetch_given_a_null_ticket_and_a_parking_boy() {
    // given
    ParkingLot parkingLot = new ParkingLot("ParkingLot_1", 10);
    StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
    standardParkingBoy.setParkingLots(Collections.singletonList(parkingLot));

    // when
    Throwable exception =
        assertThrows(
            NeedProvideParkingTicketException.class,
            () -> {
              standardParkingBoy.fetching(null);
            });

    // then
    assertEquals("Please provide your parking ticket.", exception.getMessage());
  }

  //
  //  @Test
  //  void should_get_not_enough_position_when_park_given_twenty_cars_and_a_parking_boy() {
  //    // given
  //    ParkingLot parkingLot = new ParkingLot("ParkingLot_1", 10);
  //    parkingLot.setCapacity(1);
  //    StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
  //    standardParkingBoy.setParkingLots(Collections.singletonList(parkingLot));
  //
  //    // when
  //    standardParkingBoy.parking(new Car());
  //    Throwable exception =
  //        assertThrows(
  //            NotEnoughPositionException.class,
  //            () -> {
  //              standardParkingBoy.parking(new Car());
  //            });
  //    // then
  //    assertEquals("Not enough position.", exception.getMessage());
  //  }

  //  @Test
  //  void should_get_parking_lot_1_when_park_given_1_car_and_2_parking_lots_and_1_parking_boy() {
  //    // given
  //    ParkingLot parkingLot1 = new ParkingLot("ParkingLot_1");
  //    ParkingLot parkingLot2 = new ParkingLot("ParkingLot_2");
  //    StandardParkingBoy standardParkingBoy =
  //        new StandardParkingBoy(Arrays.asList(parkingLot1, parkingLot2));
  //    Car car = new Car("A0001");
  //
  //    // when
  //    Ticket ticket = standardParkingBoy.park(car);
  //
  //    // then
  //    assertNotNull(ticket);
  //    assertEquals("ParkingLot_1", ticket.getPosition());
  //  }
  //
  //  @Test
  //  void should_get_parking_lot_2_when_park_given_11_car_2_parking_lots_and_1_parking_boy() {
  //    // given
  //    ParkingLot parkingLot1 = new ParkingLot("ParkingLot_1");
  //    ParkingLot parkingLot2 = new ParkingLot("ParkingLot_2");
  //    StandardParkingBoy standardParkingBoy =
  //        new StandardParkingBoy(Arrays.asList(parkingLot1, parkingLot2));
  //
  //    // when
  //    Ticket ticket = null;
  //    for (int i = 0; i < 11; i++) {
  //      ticket = standardParkingBoy.park(new Car("A000" + i));
  //    }
  //
  //    // then
  //    assertNotNull(ticket);
  //    assertEquals("ParkingLot_1", ticket.getPosition());
  //  }
  //
  //  @Test
  //  void should_get_parking_lot_2_when_park_given_8_car_and_2_parking_lots_and_1_parking_boy() {
  //    // given
  //    ParkingLot parkingLot1 = new ParkingLot("ParkingLot_1");
  //    ParkingLot parkingLot2 = new ParkingLot("ParkingLot_2");
  //    StandardParkingBoy standardParkingBoy =
  //        new StandardParkingBoy(Arrays.asList(parkingLot1, parkingLot2));
  //
  //    // when
  //    Ticket ticket = null;
  //    for (int i = 0; i < 8; i++) {
  //      ticket = standardParkingBoy.park(new Car("A000" + i));
  //    }
  //
  //    // then
  //    assertNotNull(ticket);
  //    assertEquals("ParkingLot_2", ticket.getPosition());
  //  }
  //
  //  @Test
  //  void should_get_parking_lot_1_when_park_given_7_cars_and_2_parking_lot_and_1_parking_boy() {
  //    // given
  //    ParkingLot parkingLot1 = new ParkingLot("ParkingLot_1");
  //    parkingLot1.setCapacity(45);
  //    ParkingLot parkingLot2 = new ParkingLot("ParkingLot_2");
  //    StandardParkingBoy standardParkingBoy =
  //        new StandardParkingBoy(Arrays.asList(parkingLot1, parkingLot2));
  //
  //    // when
  //    Ticket ticket = null;
  //    for (int i = 0; i < 7; i++) {
  //      ticket = standardParkingBoy.park(new Car("A000" + i));
  //      System.out.println(ticket.getPosition());
  //    }
  //
  //    // then
  //    assertNotNull(ticket);
  //    assertEquals("ParkingLot_2", ticket.getPosition());
  //  }
}
