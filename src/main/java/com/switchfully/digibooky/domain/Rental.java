package com.switchfully.digibooky.domain;

import com.switchfully.digibooky.domain.user.User;

import java.time.LocalDate;
import java.util.UUID;

public class Rental {
    private final Book book;
    private final User user;
	private final UUID rentalId;
	private LocalDate dueDate;

	public Rental(Book book, User user) {
		this.book = book;
        this.user = user;
		this.rentalId = UUID.randomUUID();
		this.dueDate = calculateDueDate();
	}

	private LocalDate calculateDueDate() {
		return LocalDate.now().plusDays(21);
	}

	public UUID getRentalId() {
		return rentalId;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public Book getBook() {
		return book;
	}

	public User getUser() {
		return user;
	}
}