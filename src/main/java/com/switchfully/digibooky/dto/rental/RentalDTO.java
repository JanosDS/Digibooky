package com.switchfully.digibooky.dto.rental;

import java.time.LocalDate;
import java.util.UUID;

public class RentalDTO {
    private final String isbn;
    private final UUID userId;
    private final UUID rentalId;
    private final LocalDate dueDate;

    public RentalDTO(String isbn, UUID userId, UUID rentalId, LocalDate dueDate) {
        this.isbn = isbn;
        this.userId = userId;
        this.rentalId = rentalId;
        this.dueDate = dueDate;
    }

    public String getIsbn() {
        return isbn;
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