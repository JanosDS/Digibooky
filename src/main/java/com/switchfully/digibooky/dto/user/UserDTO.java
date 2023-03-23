package com.switchfully.digibooky.dto.user;

import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.domain.user.Role;
import com.switchfully.digibooky.dto.rental.RentalDTO;
import com.switchfully.digibooky.dto.user.address.AddressDTO;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UserDTO {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDTO addressDTO;
    private Role role;

    public UserDTO(UUID userId, String firstName, String lastName, String email, AddressDTO addressDTO, Role role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressDTO = addressDTO;
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

    public AddressDTO getAddress() {
        return addressDTO;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(userId, userDTO.userId) && Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(email, userDTO.email) && Objects.equals(addressDTO, userDTO.addressDTO) && role == userDTO.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, email, addressDTO, role);
    }
}
