package com.oocl.cultivation;

public class ParkingBoy {

    public String[] park(Car[] cars) {
        String[] result = new String[cars.length];
        for (int index = 0; index < cars.length; index++) {
            result[index] = cars[index].getLicense();
        }
        return result;
    }

}
