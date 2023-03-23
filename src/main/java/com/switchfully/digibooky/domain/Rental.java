package com.switchfully.digibooky.domain;

import java.time.LocalDate;
import java.util.UUID;
import com.switchfully.digibooky.domain.user.User;

public class Rental {
    private String ISBN;
    private UUID userId;
    private UUID rentalId;
    private LocalDate dueDate;

    public Rental(Book book, User user) {
        this.ISBN = book.getISBN();
        this.userId = user.getUserId();
        this.rentalId = UUID.randomUUID();
        this.dueDate = calculateDueDate();
    }
    public Rental(String ISBN, UUID userId, UUID rentalId, LocalDate dueDate) {
        this.ISBN = ISBN;
        this.userId = userId;
        this.rentalId = rentalId;
        this.dueDate = dueDate;
    }
    private LocalDate calculateDueDate() {
        return LocalDate.now().plusDays(21);
    }
    public UUID getRentalId() {
        return rentalId;
    }

    public String getISBN() {
        return ISBN;
    }

    public UUID getUserId() {
        return userId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

}