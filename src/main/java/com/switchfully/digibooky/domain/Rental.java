package com.switchfully.digibooky.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Rental {
    private String ISBN;
    private UUID userId;
    private UUID rentalId;
    private LocalDate dueDate;

    /*
    public Rental(Book book, User user) {
        this.ISBN = book.getISBN();
        this.userId = user.getUserId();
        this.rentalId = UUID.randomUUID();
        this.dueDate = calculateDueDate();
    }
     */
}