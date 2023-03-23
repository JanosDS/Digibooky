package com.switchfully.digibooky.domain;


public class Author {
    /** field id ? **/
    private String firstName;
    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author(){
        this.firstName = null;
        this.lastName = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


}

