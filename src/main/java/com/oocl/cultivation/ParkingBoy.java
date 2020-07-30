package com.oocl.cultivation;

import java.util.List;

public class ParkingBoy {

    //    private List<Car> carList;
    private String errorMsg;
    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
//        this.carList = new ArrayList<>();
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        if (null == car) {
            return null;
        }
        int countOfHasAvailablePositionLot = 0;
        for (ParkingLot lot : this.parkingLots) {
            if (lot.getCarList().size() == 10) {
                countOfHasAvailablePositionLot++;
            }
        }
        if (countOfHasAvailablePositionLot == this.parkingLots.size()) {
            this.errorMsg = "Not enough position.";
            return null;
        }
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.hasCar(car)) {
                return null;
            }
        }
        ParkingLot lotWithMorePositions = null;
        double availablePositionRate = 0.0;
        for (ParkingLot parkingLot : parkingLots) {
            int emptyPositions = parkingLot.getCapacity() - parkingLot.getCarList().size();
            if (((double)emptyPositions / parkingLot.getCapacity()) > availablePositionRate) {
                availablePositionRate = (double)emptyPositions / parkingLot.getCapacity();
                lotWithMorePositions = parkingLot;
            }
        }
        lotWithMorePositions.parking(car);
        return new Ticket();
    }

    public Car fetch(Ticket ticket) {
        if (null == ticket) {
            this.errorMsg = "Please provide your parking ticket.";
            return null;
        }
        for (ParkingLot parkingLot : parkingLots) {
//            if (ticket.getPosition().equals(parkingLot.getName())) {
                this.errorMsg = "Unrecognized parking ticket.";
                return parkingLot.fetching(ticket);
//            }
        }
        return null;
    }

    public String queryMessage() {
        return this.errorMsg;
    }
}
