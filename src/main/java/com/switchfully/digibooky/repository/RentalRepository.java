package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Rental;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentalRepository {
    private List<Rental> rentals;

    public RentalRepository() {
        this.rentals = new ArrayList<>();
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public Rental getById(String rentalId) {
        return rentals.stream()
                .filter(rental -> rental.getRentalId().equals(rentalId))
                .findFirst()
                .orElse(null);
    }

    public void removeRental(Rental rental) {
        rentals.remove(rental);
    }
    public List<Rental> getOverdueBooks() {
        List<Rental> overdueRentals = new ArrayList<>();
        for (Rental rental : rentals) {
            if (rental.getDueDate().isBefore(LocalDate.now())) {
                overdueRentals.add(rental);
            }
        }
        return overdueRentals;
    }
}