package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookRepositoryTest {
    BookRepository bookRepository;
    @BeforeEach
    void setup() {
        this.bookRepository = new BookRepository();
    }

    @Test
    @DisplayName("Get all books in repository")
    void givenAListOfSavedBooks_whenGetAll_thenReturnAllBooks() {
        List<Author> authorList = List.of(new Author("Jimmy", "Sirius"));
        Book bookToStore1 = new Book("randomISBN12345", "LOL", "summary1", true, authorList);
        Book bookToStore2 = new Book("randomISBN12346", "LOL2","summary2", true, authorList);
        bookRepository.addBook(bookToStore1);
        bookRepository.addBook(bookToStore2);
        List<Book> actual = bookRepository.getAllBooks();
        Assertions.assertThat(actual).containsExactly(bookToStore1, bookToStore2);
    }
    @Nested
    class SearchBookByTitle {
        @Test
        @DisplayName("Search book with exact title")
        void givenAValidTitle_whenSearchingByTitle_returnTheBookWithThatTitle() {
            Book titleTest = new Book("randomISBN12345", "LOL", "summary1", true, null);
            bookRepository.addBook(titleTest);
            org.junit.jupiter.api.Assertions.assertEquals(titleTest, bookRepository.getByTitle("LOL"));
        }

        @Test
        @DisplayName("Search book with wrong title")
        void givenAWrongTitle_whenSearchingByTitle_returnNull() {
            Book titleTest = new Book("randomISBN12345", "LOL", "summary1", true, null);
            bookRepository.addBook(titleTest);
            org.junit.jupiter.api.Assertions.assertNull(bookRepository.getByTitle("wrongTitle"));
        }
    }
}