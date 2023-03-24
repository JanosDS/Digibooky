package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.dto.author.AuthorDTO;
import com.switchfully.digibooky.dto.author.AuthorMapper;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.book.BookMapper;
import com.switchfully.digibooky.dto.book.CreateBookDTO;
import com.switchfully.digibooky.dto.rental.RentalMapper;
import com.switchfully.digibooky.dto.user.UserMapper;
import com.switchfully.digibooky.dto.user.address.AddressMapper;
import com.switchfully.digibooky.exception.MandatoryFieldException;

import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.RentalRepository;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {

    private AuthorMapper authorMapper = new AuthorMapper();
    private AuthorDTO testAuthorDTO = authorMapper.mapToDto(new Author("Wouter","Sels"));
    private List<AuthorDTO> testAuthors = new ArrayList<>(List.of(testAuthorDTO));
    private BookService bookService;
    private CreateBookDTO testCreateBookDTO = new CreateBookDTO("testIsbn","testTitle",testAuthors,"test summary",true);

    @BeforeEach
    void setup(){
        this.bookService = new BookService(
                new BookMapper(authorMapper),
                new BookRepository(),
                new RentalMapper(new BookMapper(authorMapper), new UserMapper(new AddressMapper())),
                new RentalRepository(),
                new AuthorMapper());
    }
    @Nested
    @DisplayName("validate book fields")
    class BookFieldValidation{
        @Test
        @DisplayName("Validate that every mandatory field is filled in")
        void allMandatoryFieldsAreFilledIn_isValid(){
            CreateBookDTO newBook = new CreateBookDTO("test-isbn","I, Robot",testAuthors,"Test summary",true);
            bookService.validateMandatoryFields(newBook);
        }

        @Test
        @DisplayName("Validate when isbn is not filled in")
        void isbnFieldNotFilledIn_isInvalid(){
            CreateBookDTO newBook = new CreateBookDTO(null,"I, Robot",testAuthors,"Test summary",true);
            Exception exception = assertThrows(MandatoryFieldException.class, () -> {
                bookService.validateMandatoryFields(newBook);
            });
        }
        @Test
        @DisplayName("Validate when title is not filled in")
        void titleFieldNotFilledIn_isInvalid(){
            CreateBookDTO newBook = new CreateBookDTO("test isbn",null,testAuthors,"Test summary",true);
            Exception exception = assertThrows(MandatoryFieldException.class, () -> {
                bookService.validateMandatoryFields(newBook);
            });
        }
        @Test
        @DisplayName("Validate when author lastname is not filled in")
        void authorLastNameFieldNotFilledIn_isInvalid(){
            CreateBookDTO newBook = new CreateBookDTO("test isbn","I, Robot",List.of(new AuthorDTO("Test",null)),"Test summary",true);
            Exception exception = assertThrows(MandatoryFieldException.class, () -> {
                bookService.validateMandatoryFields(newBook);
            });
        }
    }
    @Nested
    @DisplayName("validate unique book")
    class BookUniqueValidation{
        @Test
        @DisplayName("Validate ISBN when it is unique")
        void uniqueIsbn_isValid(){
            String isbn = "testIsbn";
            assertTrue(bookService.isUniqueIsbn(isbn));
        }
    }
    @Nested
    @DisplayName("getBooksByTitle")
    class getBooksByTitle {
        BookRepository bookRepository = new BookRepository();
        BookMapper bookMapper = new BookMapper(authorMapper);
        List<Book> bookList;
        List<BookDTO> bookDTOList;
        List<Author> authorList = List.of(new Author("Jimmy", "Sirius"));
        Book titleTest1 = new Book("randomISBN12345", "Match", "summary1", true, authorList);
        Book titleTest2 = new Book("randomISBN12345", "Match me", "summary1", true, authorList);

        @BeforeEach
        void setup_getBooksByTitle() {
            bookRepository.addBook(titleTest1);
            bookRepository.addBook(titleTest2);
            this.bookList = bookRepository.getAllBooks();
            this.bookDTOList = bookList.stream().map(bookMapper::mapToDTO).toList();
        }

//        @Test
//        @DisplayName("Search book that contains a valid title")
//        void givenAValidTitle_returnOneBookContainingThatTitle() {
//            //given
//
//            //when
//            List<BookDTO> actual = bookService.getBooksByTitle("me");
//            //then
//            org.assertj.core.api.Assertions.assertThat(actual).containsExactly(bookMapper.mapToDTO(titleTest2));
//        }
//
//        @Test
//        @DisplayName("Search multiple books that contains a valid title")
//        void givenAValidTitle_returnBooksContainingThatTitle() {
//            //when
//            List<BookDTO> actual = bookService.getBooksByTitle("Match");
//            //then
//            org.assertj.core.api.Assertions.assertThat(actual).containsExactly(bookMapper.mapToDTO(titleTest1), bookMapper.mapToDTO(titleTest2));
//        }


        @Test
        @DisplayName("Search books that doesn't contain title")
        void givenAWrongTitle_returnNull() {
            //when
            List<BookDTO> actual = bookService.getBooksByTitle("Matchme");
            //then
            Assertions.assertTrue(actual.isEmpty());
        }
    }
}
