package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.Ticket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        //        Car car = new Car("HK-0754");
        Ticket ticket = new Ticket("HK-0754", "HK-0754-Ticket");

        // when
        Car car = parkingBoy.fetch(ticket);

        // then
        assertEquals("HK-0754", car.getLicense());
    }
}
