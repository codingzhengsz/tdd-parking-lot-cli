package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private List<Car> carList;

    public ParkingBoy() {
        this.carList = new ArrayList<>();
    }

    public Ticket park(Car car) {
        this.carList.add(car);
        return new Ticket(car.getLicense(), car.getLicense() + "-Ticket");
    }

    public Car fetch(Ticket ticket) {
        return new Car(ticket.getLicense());
    }

}
