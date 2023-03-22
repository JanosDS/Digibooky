package com.switchfully.digibooky.dto.user;

import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.domain.user.Role;

import java.util.Objects;
import java.util.UUID;

public class UserDTO {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String INSS;
    private Role role;

    public UserDTO(UUID userId, String firstName, String lastName, String email, Address address, String iNSS, Role role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.INSS = iNSS;
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

    public String getINSS() {
        return INSS;
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
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(email, userDTO.email) && Objects.equals(address, userDTO.address) && Objects.equals(INSS, userDTO.INSS) && role == userDTO.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, address, INSS, role);
    }
}
