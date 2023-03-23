package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookRepositoryTest {

    private BookRepository bookRepository;
    private Book bookToStore1;
    private Book bookToStore2;
    @BeforeEach
    void setUpObjects() {
        bookRepository = new BookRepository();
        List<Author> authorList = List.of(new Author("Jimmy", "Sirius"));
        bookToStore1 = new Book("randomISBN12345", "LOL", "summary1", true, authorList);
        bookToStore2 = new Book("randomISBN12346", "LOL2","summary2", true, authorList);
        bookRepository.putBookInList(bookToStore1);
        bookRepository.putBookInList(bookToStore2);
    }

    @Test
    void givenAListOfSavedBooks_whenGetAll_thenReturnAllBooks() {
        List<Book> actual = bookRepository.getAllBooks();
        Assertions.assertThat(actual).containsExactly(bookToStore1, bookToStore2);
    }

    @Test
    void name() {
    }
}
