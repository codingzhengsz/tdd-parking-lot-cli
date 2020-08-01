package com.oocl.cultivation;

import com.oocl.cultivation.AbstractParkingBoy;
import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AbstractParkingBoyTest {

    @Test
    void should_ensure_invoke_when_parking_given_a_car() {
        // given
        AbstractParkingBoy abstractParkingBoy = mock(AbstractParkingBoy.class, CALLS_REAL_METHODS);
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = new Car();

        when(abstractParkingBoy.findWillBeParkedParkingLot()).thenReturn(parkingLot);
        // when
        abstractParkingBoy.parking(car);

        // then
        verify(abstractParkingBoy, times(1)).findWillBeParkedParkingLot();
        verify(parkingLot, times(1)).park(car);
    }
}
