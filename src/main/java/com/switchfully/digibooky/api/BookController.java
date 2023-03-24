package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.user.Feature;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.book.BookDetailDTO;
import com.switchfully.digibooky.dto.book.BookUpdateDTO;
import com.switchfully.digibooky.dto.book.CreateBookDTO;
import com.switchfully.digibooky.service.BookService;
import com.switchfully.digibooky.service.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;
    private final SecurityService securityService;

    public BookController(BookService bookService, SecurityService securityService) {
        this.bookService = bookService;
        this.securityService = securityService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAllBooks(@RequestParam(required = false, name="title") String title, @RequestParam(required = false, name="author")String author){
        if(title != null){
            return bookService.getBooksByTitle(title) ;
        }
        if (author != null)
        {
            return bookService.getBookByAuthor(author);
        }
        return bookService.getAllBooks();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{isbn}", produces = "application/json")
    public List<BookDTO> getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBooksByIsbn(isbn);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "{isbn}/details", produces = "application/json")
    public BookDetailDTO getBookDetailsByIsbn(@PathVariable String isbn) {
        return bookService.getBookDetailByIsbn(isbn);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public BookDTO createBook(@RequestBody CreateBookDTO createBookDTO, @RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.CREATE_BOOK);
        return bookService.createBook(createBookDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = "application/json", produces = "application/json", path = "/{isbn}")
    public BookDTO updateBook(@RequestBody BookUpdateDTO bookUpdateDTO, @PathVariable String isbn, @RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.CREATE_BOOK);
        return bookService.updateBook(bookUpdateDTO,isbn);
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
