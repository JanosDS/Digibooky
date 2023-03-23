package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.book.BookMapper;
import com.switchfully.digibooky.dto.rental.RentalDTO;
import com.switchfully.digibooky.dto.rental.RentalMapper;
import com.switchfully.digibooky.exception.UserNotFoundException;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.RentalRepository;
import com.switchfully.digibooky.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RentalService {
    //implement services
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final UserRepository userRepository;

    public RentalService(RentalRepository rentalRepository, RentalMapper rentalMapper, BookRepository bookRepository, BookMapper bookMapper, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.userRepository = userRepository;
    }

    public void rentBook(String title, String lastName, String firstName) {
        String ISBN = bookRepository.getBookByTitle(title).getISBN();
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

    public List<RentalDTO> getRentalsByUserId(UUID userId) {
        List<Rental> rentalsByUserId = rentalRepository.getByUserId(userId);
        return rentalMapper.mapToDTO(rentalsByUserId);
    }

    public List<BookDTO> getBooksBorrowedByUser(UUID userId) {
        List<Book> booksByUser = getRentalsByUserId(userId).stream()
                .map(rentalDTO -> bookRepository.getById(rentalDTO.getISBN()))
                .toList();
        return bookMapper.mapToDTO(booksByUser);
    }
}