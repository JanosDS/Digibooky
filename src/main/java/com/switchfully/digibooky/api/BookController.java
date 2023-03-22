package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.book.Book;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.book.BookUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.switchfully.digibooky.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
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
}
