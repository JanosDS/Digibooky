package com.switchfully.digibooky.dto.user;

import com.switchfully.digibooky.domain.Address;

import javax.management.relation.Role;
import java.util.UUID;

public class UserDTO {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String iNSS;
    private Role role;

    public UserDTO(UUID userId, String firstName, String lastName, String email, Address address, String iNSS, Role role) {
        this.userId = userId;
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
