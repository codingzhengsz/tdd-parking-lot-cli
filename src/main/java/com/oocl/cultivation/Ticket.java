package com.oocl.cultivation;

public class Ticket {

    private String license;
    private String number;
    private String position;

    public Ticket(String license, String number, String position) {
        this.license = license;
        this.number = number;
        this.position = position;
    }

    public String getLicense() {
        return license;
    }

    public String getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }
}
