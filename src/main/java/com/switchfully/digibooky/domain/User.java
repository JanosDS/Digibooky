package com.switchfully.digibooky.domain;

import java.util.UUID;

public class User {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String iNSS;
    private Role role;

    public User(String firstName, String lastName, String email, Address address, String iNSS, Role role) {
        this.userId = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.iNSS = iNSS;
        this.role = role;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getiNSS() {
        return iNSS;
    }

    public Role getRole() {
        return role;
    }
}