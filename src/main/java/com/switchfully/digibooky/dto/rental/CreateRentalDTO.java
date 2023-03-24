package com.switchfully.digibooky.dto.rental;

import java.util.UUID;

public class CreateRentalDTO {

	private final UUID userId;
	private final String isbn;

	public CreateRentalDTO(UUID userId, String isbn) {
		this.userId = userId;
		this.isbn = isbn;
	}

	public UUID getUserId() {
		return userId;
	}

	public String getIsbn() {
		return isbn;
	}
}
