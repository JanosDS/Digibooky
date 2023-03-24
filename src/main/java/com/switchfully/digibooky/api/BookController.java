package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.user.Feature;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.book.BookDetailDTO;
import com.switchfully.digibooky.dto.book.BookUpdateDTO;
import com.switchfully.digibooky.dto.book.CreateBookDTO;
import com.switchfully.digibooky.service.BookService;
import com.switchfully.digibooky.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/book")
public class BookController {

	private final BookService bookService;
	private final SecurityService securityService;

	@Autowired
	public BookController(BookService bookService, SecurityService securityService) {
		this.bookService = bookService;
		this.securityService = securityService;
	}
    @ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = "application/json")
	public List<BookDTO> getAllBooks(@RequestParam(required = false, name = "title") String title, @RequestParam(required = false, name = "author") String author) {
		if (Objects.nonNull(title)) {
			return bookService.getBooksByTitle(title);
		} else if (Objects.nonNull(author)) {
			return bookService.getBookByAuthor(author);
		} else {
			return bookService.getAllBooks();
		}
	}
    @ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/{isbn}", produces = "application/json")
	public List<BookDTO> getBookByIsbn(@PathVariable String isbn) {
		return bookService.getBookListByIsbn(isbn);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "{isbn}/details", produces = "application/json")
	public BookDetailDTO getBookDetailsByIsbn(@PathVariable String isbn) {
		return bookService.getBookDetailByIsbn(isbn);
	}


	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public BookDTO createBook(@RequestBody CreateBookDTO createBookDTO, @RequestHeader String authorization) {
		securityService.validateAuthorization(authorization, Feature.CREATE_BOOK);
		return bookService.createBook(createBookDTO);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(consumes = "application/json", produces = "application/json")
	public BookDTO updateBook(@RequestBody BookUpdateDTO bookUpdateDTO, @RequestHeader String authorization) {
		securityService.validateAuthorization(authorization, Feature.CREATE_BOOK);
		return bookService.updateBook(bookUpdateDTO);
	}
    @ResponseStatus(HttpStatus.OK)
	@DeleteMapping(path = "/{isbn}")
	public BookDTO deleteBook(@PathVariable String isbn, @RequestHeader String authorization) {
		securityService.validateAuthorization(authorization, Feature.DELETE_BOOK);
		return bookService.deleteBook(isbn);
	}
    @ResponseStatus(HttpStatus.OK)
	@PutMapping(produces = "application/json", path = "/{isbn}")
	public BookDTO unDeleteBook(@PathVariable String isbn, @RequestHeader String authorization) {
		securityService.validateAuthorization(authorization, Feature.UNDELETE_BOOK);
		return bookService.unDeleteBook(isbn);
	}

}
