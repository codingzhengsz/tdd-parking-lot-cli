package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private List<Car> carList;
    private String errorMsg;

    public ParkingBoy() {
        this.carList = new ArrayList<>();
    }

    public Ticket park(Car car) {
        if (null == car) {
            return null;
        }
        if (this.carList.size() == 20) {
            this.errorMsg = "Not enough position.";
            return null;
        }
        for (Car parkedCar : carList) {
            if (parkedCar.getLicense().equals(car.getLicense())) {
                return null;
            }
        }
        this.carList.add(car);
        if (this.carList.size() % 2 == 0) {
            return new Ticket(car.getLicense(), car.getLicense() + "-Ticket", "ParkingLot_2");
        }
        return new Ticket(car.getLicense(), car.getLicense() + "-Ticket", "ParkingLot_1");
    }

    public Car fetch(Ticket ticket) {
        if (null == ticket) {
            this.errorMsg = "Please provide your parking ticket.";
            return null;
        }
        for (Car car : carList) {
            if (car.getLicense().equals(ticket.getLicense())) {
                this.carList.remove(car);
                return car;
            }
        }
        this.errorMsg = "Unrecognized parking ticket.";
        return null;
    }

    public String queryMessage() {
        return this.errorMsg;
    }
}
