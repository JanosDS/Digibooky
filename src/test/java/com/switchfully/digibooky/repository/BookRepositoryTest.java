package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookRepositoryTest {

    private BookRepository bookRepository;
    private Book book1;
    private Book book2;
    @BeforeEach
    void setUpObjects() {
        bookRepository = new BookRepository();
        List<Author> authorList = List.of(new Author("Jimmy", "Sirius"));
        book1 = new Book("randomISBN12345", "LOL", "summary1", true, authorList);
        book2 = new Book("randomISBN12346", "LOL2","summary2", true, authorList);
        bookRepository.putBookInList(book1);
        bookRepository.putBookInList(book2);
    }

    @Test
    void givenAListOfSavedBooks_whenGetAll_thenReturnAllBooks() {
        List<Book> actual = bookRepository.getAllBooks();
        Assertions.assertThat(actual).containsExactly(book1, book2);
    }

    @Test
    void givenIsbnOfBook_whenDeleteBook_thenMoveBookToDeletedBooks() {
        bookRepository.deleteBook(book1);
        Assertions.assertThat(bookRepository.getBookList()).containsExactly(book2);
        Assertions.assertThat(bookRepository.getDeletedBooks()).containsExactly(book1);
    }

    @Test
    void givenIsbnOfBook_whenUnDeleteBook_thenMoveBookToUnDeletedBooks() {
        bookRepository.deleteBook(book1);
        bookRepository.unDeleteBook(book1);
        Assertions.assertThat(bookRepository.getBookList()).containsExactly(book2, book1);
        Assertions.assertThat(bookRepository.getDeletedBooks()).isEmpty();
    }
}
