package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.Ticket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {
    @Test
    void should_return_ticket_when_park_given_a_car_and_a_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("HK-0754");

        // when
        Ticket ticket = parkingBoy.park(car);

        // then
        assertEquals("HK-0754-Ticket", ticket.getNumber());
    }

    @Test
    void should_return_tickets_when_park_given_2_car_and_a_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car carA = new Car("HK-0754");
        Car carB = new Car("HK-0756");

        // when
        Ticket ticketA = parkingBoy.park(carA);
        Ticket ticketB = parkingBoy.park(carB);

        // then
        assertEquals("HK-0754-Ticket", ticketA.getNumber());
        assertEquals("HK-0756-Ticket", ticketB.getNumber());
    }

    @Test
    void should_return_a_car_when_fetch_given_a_ticket_and_a_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("HK-0754");
        parkingBoy.park(car);
        Ticket ticket = new Ticket("HK-0754", "HK-0754-Ticket", "ParkingLot_1");

        // when
        Car fetchedCar = parkingBoy.fetch(ticket);

        // then
        assertEquals("HK-0754", fetchedCar.getLicense());
    }

    @Test
    void should_return_no_car_when_fetch_given_a_wrong_ticket_and_a_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("A0001");
        parkingBoy.park(car);
        Ticket ticket = new Ticket("A0002", "A0002-Ticket", "ParkingLot_1");

        // when
        Car fetchedCar = parkingBoy.fetch(ticket);

        // then
        assertNull(fetchedCar);
    }

    @Test
    void should_return_no_car_when_fetch_given_a_used_ticket_and_a_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("A0001");
        Ticket ticket = parkingBoy.park(car);

        // when
        parkingBoy.fetch(ticket);
        Car fetchedCar = parkingBoy.fetch(ticket);

        // then
        assertNull(fetchedCar);
    }

    @Test
    void should_get_no_ticket_when_park_given_twenty_cars_and_a_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();

        // when
        Ticket ticket = null;
        for (int i = 0; i <= 20; i++) {
            ticket = parkingBoy.park(new Car("A000" + i));
        }

        // then
        assertNull(ticket);
    }

    @Test
    void should_get_no_ticket_when_park_given_a_parked_car_and_a_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car parkedCar = new Car("A0001");
        parkingBoy.park(parkedCar);

        // when
        Ticket ticket = parkingBoy.park(parkedCar);

        // then
        assertNull(ticket);
    }

    @Test
    void should_get_no_ticket_when_park_given_a_null_car_and_a_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();

        // when
        Ticket ticket = parkingBoy.park(null);

        // then
        assertNull(ticket);
    }

    @Test
    void should_get_unrecognized_parking_ticket_msg_when_fetch_given_a_wrong_ticket_and_a_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("A0001");
        Ticket ticket = parkingBoy.park(car);

        // when
        parkingBoy.fetch(ticket);
        Car fetchedCar = parkingBoy.fetch(ticket);
        String msg = parkingBoy.queryMessage();

        // then
        assertNull(fetchedCar);
        assertEquals("Unrecognized parking ticket.", msg);
    }

    @Test
    void should_get_need_provide_ticket_msg_when_fetch_given_a_null_ticket_and_a_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();

        // when
        Car car = parkingBoy.fetch(null);
        String msg = parkingBoy.queryMessage();

        // then
        assertNull(car);
        assertEquals("Please provide your parking ticket.", msg);
    }

    @Test
    void should_get_not_enough_position_when_park_given_twenty_cars_and_a_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();

        // when
        Ticket ticket = null;
        for (int i = 0; i <= 20; i++) {
            ticket = parkingBoy.park(new Car("A000" + i));
        }
        String msg = parkingBoy.queryMessage();
        // then
        assertNull(ticket);
        assertEquals("Not enough position.", msg);
    }

    @Test
    void should_get_parking_lot_1_when_park_given_1_car_and_2_parking_lots_and_1_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("A0001");

        // when
        Ticket ticket = parkingBoy.park(car);

        // then
        assertNotNull(ticket);
        assertEquals("ParkingLot_1", ticket.getPosition());
    }

    @Test
    void should_get_parking_lot_2_when_park_given_11_car_2_parking_lots_and_1_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();

        // when
        Ticket ticket = null;
        for (int i = 0; i < 11; i++) {
            ticket = parkingBoy.park(new Car("A000" + i));
        }

        // then
        assertNotNull(ticket);
        assertEquals("ParkingLot_2", ticket.getPosition());
    }
}
