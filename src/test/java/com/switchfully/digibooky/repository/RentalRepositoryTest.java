package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.domain.user.Role;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.dto.user.UserDTO;
import com.switchfully.digibooky.service.RentalService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalRepositoryTest {
    @Test
    void whenAskingListOfRentals_thenAListIsReturned() {
        //given
        RentalRepository rentalRepository = new RentalRepository();
        List<Author> authorList = new ArrayList(List.of(new Author("Jimmy", "Hendrix")));
        Address address = new Address("street", "number", "postalCode", "city", "country");
        Rental rental = new Rental(new Book("isbn", "title", "summary",true, authorList),
                new User("firstName","lastName","email@email.com",address, "inss", Role.MEMBER));
        rentalRepository.addRental(rental);
        //when
        List<Rental> toCheckList = rentalRepository.getRentals();
        //then
        Assertions.assertThat(toCheckList).contains(rental);
    }

    @Test
    void whenCreatingARental_thenReturnDateIsCalculated(){
        //given
        RentalRepository rentalRepository = new RentalRepository();
        List<Author> authorList = new ArrayList(List.of(new Author("Jimmy", "Hendrix")));
        Address address = new Address("street", "number", "postalCode", "city", "country");
        Rental rental = new Rental(new Book("isbn", "title", "summary",true, authorList),
                new User("firstName","lastName","email@email.com",address, "inss", Role.MEMBER));

        //when
        LocalDate toCheckDate = rental.getDueDate();

        //then
        Assertions.assertThat(toCheckDate).isEqualTo(LocalDate.now().plusDays(21));
    }

   /* @Test
    void whenReturningABookAndOverdue_thenExceptionIsThrown() {
        //given
        BookRepository bookRepository = new BookRepository();
        UserRepository userRepository = new UserRepository();
        RentalService rentalService = new RentalService(new RentalRepository(), bookRepository, userRepository);
        List<Author> authorList = new ArrayList(List.of(new Author("Jimmy", "Hendrix")));
        Address address = new Address("street", "number", "postalCode", "city", "country");
        User user = new User("firstName","lastName","email@email.com",address, "inss", Role.MEMBER);
        UserDTO userDTO = new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress(), user.getInss(), user.getRole());
        Book book = new Book("isbn", "title", "summary",true, authorList);
        bookRepository.addBook(book);
        userRepository.addUser(user);
        //when
       Rental rental = new Rental(book, user);
       rental.setDueDate(LocalDate.now().minusDays(1));
       rentalService.rentBook(book.getTitle(), userDTO);
//then
        Assertions.assertThatThrownBy(() -> rentalService.returnBook(rental))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Book is overdue");

    }*/
}
