package com.oocl.cultivation;

public class Ticket {

    private String license;
    private String number;

    public Ticket(String license, String number) {
        this.license = license;
        this.number = number;
    }

    public String getLicense() {
        return license;
    }

    public String getNumber() {
        return number;
    }
}
