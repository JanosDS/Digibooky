package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.domain.user.Role;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.dto.author.AuthorMapper;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.book.BookMapper;
import com.switchfully.digibooky.dto.rental.RentalMapper;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.RentalRepository;
import com.switchfully.digibooky.repository.UserRepository;
import groovy.xml.StreamingDOMBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RentalServiceTest {

    RentalRepository rentalRepository;
    RentalService rentalService;
    BookMapper bookMapper;
    BookRepository bookRepository;

    @BeforeEach
    void setup() {
        this.rentalRepository = new RentalRepository();
        this.bookMapper = new BookMapper(new AuthorMapper());
        this.rentalService = new RentalService(rentalRepository, new RentalMapper(), new BookRepository(), bookMapper, new UserRepository());
        this.bookRepository = new BookRepository();
    }
//    @Test
//    void givenAUserId_returnAllBooksUserBorrowed() {
//        //given
//        List<Author> authorList = new ArrayList(List.of(new Author("Jimmy", "Hendrix")));
//        Address address = new Address("street", "number", "postalCode", "city", "country");
//        User user1 = new User("firstName","lastName","email@email.com",address, "inss", Role.MEMBER);
//        User user2 = new User("firstName","lastName","email@email.com",address, "inss", Role.MEMBER);
//
//        Book book1 = new Book("isbn", "title", "summary",true, authorList);
//        Book book2 = new Book("isbn2", "title2", "summary",true, authorList);
//        Book book3 = new Book("isbn3", "title3", "summary",true, authorList);
//        bookRepository.addBook(book1);
//        bookRepository.addBook(book2);
//        bookRepository.addBook(book3);
//        Rental rental1 = new Rental(book1, user1);
//        Rental rental2 = new Rental(book2, user1);
//        Rental rental3 = new Rental(book3, user2);
//        rentalRepository.addRental(rental1);
//        rentalRepository.addRental(rental2);
//        rentalRepository.addRental(rental3);
//        //when
////        List<BookDTO> expected = new ArrayList<>(List.of(bookMapper.mapToDTO(book1), bookMapper.mapToDTO(book2), bookMapper.mapToDTO(book3)));
//        List<BookDTO> actual = rentalService.getBooksBorrowedByUser(user1.getUserId());
//        //then
//        Assertions.assertThat(actual).containsExactly(bookMapper.mapToDTO(book1), bookMapper.mapToDTO(book2));
//
//    }
}
