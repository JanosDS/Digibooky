package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Rental;
import org.springframework.stereotype.Repository;

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
}