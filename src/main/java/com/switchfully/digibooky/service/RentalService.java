package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.domain.user.Feature;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.book.BookMapper;
import com.switchfully.digibooky.dto.rental.RentalDTO;
import com.switchfully.digibooky.dto.rental.RentalMapper;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.book.BookMapper;
import com.switchfully.digibooky.dto.user.UserDTO;
import com.switchfully.digibooky.exception.UserNotFoundException;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.RentalRepository;
import com.switchfully.digibooky.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RentalService {
    //implement services
    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    private final RentalMapper rentalMapper;
    private final UserRepository userRepository;
    private final BookMapper bookMapper;

    @Autowired
    public RentalService(RentalRepository rentalRepository, RentalMapper rentalMapper, BookRepository bookRepository, BookMapper bookMapper, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.userRepository = userRepository;
    }

    public void rentBook(String title, UserDTO userDTO) {
        String isbn = bookRepository.getBookByTitle(title).getIsbn();
        Book book = bookRepository.getByIsbn(isbn);
        if (book.isAvailable()) {
            Rental rental = new Rental(bookRepository.getByIsbn(isbn), userRepository.getUserByUUID(userDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User not found")));
            rentalRepository.addRental(rental);
            book.setAvailable(false);
        }
    }

    public void returnBook(Rental rental) {

        String isbn = rental.getIsbn();
        Book book = bookRepository.getByIsbn(isbn);
        UUID userId = rental.getUserId();
        User user = userRepository.getUserByUUID(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        if (rental.getIsbn().equals(isbn) && rental.getUserId().equals(user.getUserId())) {
            if (rental.getDueDate().isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Book is overdue");
            }
            rentalRepository.removeRental(rental);
            book.setAvailable(true);
        } else {
            //make custom exception
            throw new IllegalArgumentException("Book is not rented by this user");
        }
    }

    public List<BookDTO> getOverdueBooks() {
        return rentalRepository.getOverdueBooks().stream()
                .map(rental -> bookRepository.getById(rental.getIsbn()))
                .map(book -> bookMapper.mapToDTO(book))
                .collect(Collectors.toList());
    }


    public List<RentalDTO> getRentalsByUserId(UUID userId) {
        List<Rental> rentalsByUserId = rentalRepository.getByUserId(userId);
        return rentalMapper.mapToDTO(rentalsByUserId);
    }

    public List<BookDTO> getBooksBorrowedByUser(String userId) {
        UUID uuid;
        try {
            uuid = UUID.fromString(userId);
        } catch (IllegalArgumentException exception){
            throw new UserNotFoundException(userId + " is not a valid user");
        }
        List<Book> booksByUser = getRentalsByUserId(uuid).stream()
                .map(rentalDTO -> bookRepository.getById(rentalDTO.getIsbn()))
                .toList();
        return bookMapper.mapToDTO(booksByUser);
    }
}