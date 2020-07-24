package com.oocl.cultivation;

public class ParkingBoy {

    public Ticket park(Car car) {
        return new Ticket(car.getLicense(), car.getLicense() + "-Ticket");
    }

    public Car fetch(Ticket ticket) {
        return new Car(ticket.getLicense());
    }

}
