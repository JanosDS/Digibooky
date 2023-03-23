package com.switchfully.digibooky.domain.user;

import java.util.Objects;
import java.util.UUID;

public class User {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String inss;
    private Role role;

    public User(String firstName, String lastName, String email, Address address, String inss, Role role) {
        this.userId = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.inss = inss;
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

    public String getInss() {
        return inss;
    }

    public Role getRole() {
        return role;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(email, user.email) && Objects.equals(inss, user.inss);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, inss);
    }
}