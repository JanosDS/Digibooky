package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.domain.user.Role;
import com.switchfully.digibooky.domain.user.User;
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
}
