package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Rental;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentalRepository {
    List<Rental> rentals;
}