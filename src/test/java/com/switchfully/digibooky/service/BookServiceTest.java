package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.dto.author.AuthorDTO;
import com.switchfully.digibooky.dto.author.AuthorMapper;
import com.switchfully.digibooky.dto.book.BookMapper;
import com.switchfully.digibooky.dto.book.CreateBookDTO;
import com.switchfully.digibooky.exception.MandatoryFieldException;

import com.switchfully.digibooky.repository.BookRepository;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {

    private AuthorMapper authorMapper = new AuthorMapper();
    private AuthorDTO testAuthor1 = authorMapper.mapToDto(new Author("Wouter","Sels"));
    private List<AuthorDTO> testAuthors = new ArrayList<>(List.of(testAuthor1));
    //CreateBookDTO newBook = new CreateBookDTO("testIsbn", "testTitle", testAuthors, "Test summary",true);

    private BookService bookService;

    @BeforeEach
    void setup(){
        this.bookService = new BookService(new BookMapper(authorMapper),new BookRepository());
    }
    @Nested
    @DisplayName("validate book fields")
    class BookValidation{
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
            CreateBookDTO newBook = new CreateBookDTO("test isbn","I, Robot",List.of(new AuthorDTO().setFirstName("Test").setLastName(null)),"Test summary",true);
            Exception exception = assertThrows(MandatoryFieldException.class, () -> {
                bookService.validateMandatoryFields(newBook);
            });
        }

    }
}
