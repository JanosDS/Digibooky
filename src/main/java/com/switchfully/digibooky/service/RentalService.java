package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.exception.UserNotFoundException;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.RentalRepository;
import com.switchfully.digibooky.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RentalService {
    //implement services
    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    public RentalService(RentalRepository rentalRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public void rentBook(String title, String lastName, String firstName) {
        String ISBN = bookRepository.getBookByTitle(title).getIsbn();
        Book book = bookRepository.getById(ISBN);
        User user = userRepository.getUserByName(lastName, firstName)
                .orElseThrow(() -> new UserNotFoundException("User could not be found."));

        if(book.isAvailable()) {
            Rental rental = new Rental(book, user);
            rentalRepository.addRental(rental);
            book.setAvailable(false);
        }
        else {
            throw new IllegalArgumentException("Book is not available");
        }
    }
}