package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.author.Author;
import com.switchfully.digibooky.domain.book.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookRepositoryTest {

    @Test
    void givenAListOfSavedBooks_whenGetAll_thenReturnAllBooks() {
        BookRepository bookRepository = new BookRepository();
        List<Author> authorList = List.of(new Author("Jimmy", "Sirius"));
        Book bookToStore1 = new Book("randomISBN12345", "LOL", authorList, "summary1", true);
        Book bookToStore2 = new Book("randomISBN12346", "LOL2", authorList, "summary2", true);
        bookRepository.putBookInList(bookToStore1);
        bookRepository.putBookInList(bookToStore2);
        List<Book> actual = bookRepository.getAll();
        Assertions.assertThat(actual).containsExactly(bookToStore1, bookToStore2);
    }
}
