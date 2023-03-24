package com.switchfully.digibooky.dto.author;

public class AuthorDTO {
    private final String firstName;
    private final String lastName;

    public AuthorDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
}
