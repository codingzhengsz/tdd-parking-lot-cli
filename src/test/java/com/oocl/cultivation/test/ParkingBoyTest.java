package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ParkingBoyTest {
    @Test
    void should_return_ticket_when_given_a_car_and_a_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("HK-0754");

        // when
        String[] ticket = parkingBoy.park(new Car[]{car});

        // then
        assertArrayEquals(new String[]{"HK-0754"}, ticket);
    }


}
