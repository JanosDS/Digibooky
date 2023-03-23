package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.user.Feature;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.user.UserDTO;
import com.switchfully.digibooky.service.BookService;
import com.switchfully.digibooky.service.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.switchfully.digibooky.dto.book.BookUpdateDTO;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private SecurityService securityService;

    public BookController(BookService bookService, SecurityService securityService) {
        this.bookService = bookService;
        this.securityService = securityService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAllBooks(){
        return bookService.getAllBooks();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{id}", produces = "application/json")
    public List<BookDTO> getBookById(@PathVariable String id) {
        return bookService.getBooksByIsbn(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        return bookService.createBook(bookDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = "application/json", produces = "application/json", path = "/{id}")
    public BookDTO updateBook(@RequestBody BookUpdateDTO bookUpdateDTO, @PathVariable String id) {
        BookDTO bookDTO = bookService.getBookById(id);
        bookDTO.setTitle(bookUpdateDTO.getTitle());
        bookDTO.setSummary(bookUpdateDTO.getSummary());
        bookDTO.setAuthorList(bookUpdateDTO.getAuthorList());
        bookDTO.setAvailable(bookUpdateDTO.isAvailable());
        return bookService.updateBook(bookDTO);
    }

    @DeleteMapping(path = "/{id}")
    public BookDTO deleteBook(@PathVariable String id, @RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.DELETE_BOOK);
        return bookService.deleteBook(id);
    }

    @PutMapping(path = "/{id}")
    public BookDTO unDeleteBook(@PathVariable String id, @RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.UNDELETE_BOOK);
        return bookService.unDeleteBook(id);
    }

}
