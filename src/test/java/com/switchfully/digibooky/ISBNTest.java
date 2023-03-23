package com.switchfully.digibooky;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.dto.author.AuthorMapper;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.book.BookMapper;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.service.BookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ISBNTest {
//    @Test
//    void name() {
//
//        BookRepository bookRepository = new BookRepository();
//        BookMapper bookMapper = new BookMapper(new AuthorMapper());
//        BookService bookService = new BookService(bookMapper, bookRepository);
//        List<Author> authorList = List.of(new Author("Jimmy", "Sirius"));
//        Book bookToStore1 = new Book("randomISBN12345", "LOL", authorList, "summary", true);
//        bookRepository.putBookInList(bookToStore1);
//        List<BookDTO> bookToTest =  bookService.getBooksByIsbn("randomISBN12*");
//        String ISBN1 = bookToTest.get(0).getIsbn();
//        Assertions.assertThat(ISBN1).isEqualTo("randomISBN12345");
//    }
}
