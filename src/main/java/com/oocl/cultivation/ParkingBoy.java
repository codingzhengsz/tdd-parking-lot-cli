package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private List<Car> carList;

    public ParkingBoy() {
        this.carList = new ArrayList<>();
    }

    public Ticket park(Car car) {
        if (this.carList.size() == 10) {
            return null;
        }
        for (Car parkedCar : carList) {
            if (parkedCar.getLicense().equals(car.getLicense())) {
                return null;
            }
        }
        this.carList.add(car);
        return new Ticket(car.getLicense(), car.getLicense() + "-Ticket");
    }

    public Car fetch(Ticket ticket) {
        if (null == ticket) {
            return null;
        }
        for (Car car : carList) {
            if (car.getLicense().equals(ticket.getLicense())) {
                this.carList.remove(car);
                return car;
            }
        }
        return null;
    }

    public String queryMessage() {
        return "Unrecognized parking ticket.";
    }
}
