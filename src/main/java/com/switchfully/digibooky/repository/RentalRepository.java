package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Rental;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class RentalRepository {
	private List<Rental> rentals;
	private List<Rental> archivedRentals;

	public RentalRepository() {
		this.rentals = new ArrayList<>();
		this.archivedRentals = new ArrayList<>();
	}

	public Rental addRental(Rental rental) {
		rentals.add(rental);
        return rental;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public Optional<Rental> getById(String rentalId) {
		return rentals.stream()
				.filter(rental -> rental.getRentalId().toString().equals(rentalId))
				.findFirst();
	}

	public Rental removeRental(Rental rental) {
		archivedRentals.add(rental);
		rentals.remove(rental);
        return rental;
	}

	public List<Rental> getOverdueBooks() {
		return rentals.stream()
				.filter(rental -> rental.getDueDate().isBefore(LocalDate.now()))
				.collect(Collectors.toList());
	}

	public Optional<Rental> getRentalByIsbn(String isbn) {
		return rentals.stream()
				.filter(rental -> rental.getBook().getIsbn().equals(isbn))
				.findFirst();
	}

	public List<Rental> getRentalsByUserId(UUID userId) {
		return rentals.stream()
				.filter(rental -> rental.getUser().getUserId().equals(userId))
				.collect(Collectors.toList());
	}
}