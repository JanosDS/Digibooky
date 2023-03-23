package com.switchfully.digibooky.dto.rental;

import java.time.LocalDate;
import java.util.UUID;

public class RentalDTO {
    private String ISBN;
    private UUID userId;
    private UUID rentalId;
    private LocalDate dueDate;

    public RentalDTO(String ISBN, UUID userId, UUID rentalId, LocalDate dueDate) {
        this.ISBN = ISBN;
        this.userId = userId;
        this.rentalId = rentalId;
        this.dueDate = dueDate;
    }

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