package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    String name;
    int capacity;
    List<Car> carList;

    public ParkingLot(String name) {
        this.name = name;
        this.capacity = 10;
        this.carList = new ArrayList<>(10);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void parking(Car car) {
        this.carList.add(car);
    }

    public Car fetching(Ticket ticket) {
        for (Car car : carList) {
            if (car.getLicense().equals(ticket.getLicense())) {
                this.carList.remove(car);
                return car;
            }
        }
        return null;
    }

    public boolean hasCar(Car car) {
        for (Car parkedCar : carList) {
            if (parkedCar.getLicense().equals(car.getLicense())) {
                return true;
            }
        }
        return false;
    }

    public List<Car> getCarList() {
        return carList;
    }
}
