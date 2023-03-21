package com.switchfully.digibooky.dto;

import java.time.LocalDate;
import java.util.UUID;

public class RentalDTO {
    private String ISBN;
    private UUID userId;
    private UUID rentalId;
    private LocalDate dueDate;

    public String getISBN() {
        return ISBN;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getRentalId() {
        return rentalId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}