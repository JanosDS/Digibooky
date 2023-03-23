package com.switchfully.digibooky.domain;

import java.time.LocalDate;
import java.util.UUID;
import com.switchfully.digibooky.domain.user.User;

public class Rental {
    private String isbn;
    private final UUID userId;
    private final UUID rentalId;
    private final LocalDate dueDate;

    public Rental(Book book, User user) {
        this.isbn = book.getIsbn();
        this.userId = user.getUserId();
        this.rentalId = UUID.randomUUID();
        this.dueDate = calculateDueDate();
    }
    public Rental(String isbn, UUID userId, UUID rentalId, LocalDate dueDate) {
        this.isbn = isbn;
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

    public String getIsbn() {
        return isbn;
    }

    public UUID getUserId() {
        return userId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}