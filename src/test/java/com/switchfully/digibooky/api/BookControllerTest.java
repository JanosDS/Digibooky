package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.author.Author;
import com.switchfully.digibooky.domain.book.Book;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.service.BookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class BookControllerTest {

    @Value("8080")
    private int port;

    @Test
    void givenBookList_whenGetAllBooks_thenReturnListInJSON() {
        BookRepository bookRepository = new BookRepository();
        List<Author> authorList = List.of(new Author("Jimmy", "Sirius"));
        Book bookToStore1 = new Book("randomISBN12345", "LOL", authorList, "summary1", true);
        bookRepository.putBookInList(bookToStore1);


    }
}
