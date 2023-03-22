package com.switchfully.digibooky.dto.author;

public class AuthorDto {
    private String firstName;
    private String lastName;

    public AuthorDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AuthorDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
