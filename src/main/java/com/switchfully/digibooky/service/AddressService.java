package com.switchfully.digibooky.service;

public class AddressService {
    private final String street;
    private final String houseNumber;
    private final String postalCode;
    private final String city;
    private final String country;

    public AddressService(String street, String houseNumber, String postalCode, String city, String country) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }
}
