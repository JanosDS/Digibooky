package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookRepositoryTest {

    @Test
    void givenAListOfSavedBooks_whenGetAll_thenReturnAllBooks() {
        BookRepository bookRepository = new BookRepository();
        List<Author> authorList = List.of(new Author("Jimmy", "Sirius"));
        Book bookToStore1 = new Book("randomISBN12345", "LOL", "summary1", true, authorList);
        Book bookToStore2 = new Book("randomISBN12346", "LOL2","summary2", true, authorList);
        bookRepository.addBook(bookToStore1);
        bookRepository.addBook(bookToStore2);
        List<Book> actual = bookRepository.getAllBooks();
        Assertions.assertThat(actual).containsExactly(bookToStore1, bookToStore2);
    }
}
