package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.user.Feature;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.rental.RentalDTO;
import com.switchfully.digibooky.dto.user.UserDTO;
import com.switchfully.digibooky.service.RentalService;
import com.switchfully.digibooky.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/rental")
public class RentalController {

	private final RentalService rentalService;
	private final SecurityService securityService;

	@Autowired
	public RentalController(SecurityService securityService, RentalService rentalService) {
		this.securityService = securityService;
		this.rentalService = rentalService;
	}

    @ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = "application/json", path = "/overdue")
	public List<BookDTO> getOverdueBooks() {
		return rentalService.getOverdueBooks();
	}
    @ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public RentalDTO createRental(@RequestBody String isbn, @RequestBody UserDTO userDTO) {
		return rentalService.rentBook(isbn, userDTO);
	}
    @ResponseStatus(HttpStatus.OK)
	@DeleteMapping(produces = "application/json", path = "/{rentalId}")
	public RentalDTO returnBook(@PathVariable String rentalId) {
		return rentalService.returnBook(rentalId);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = "application/json", path = "/{id}")
	public List<BookDTO> getBooksBorrowedByUser (@RequestHeader String authorization, @PathVariable String id) {
		securityService.validateAuthorization(authorization, Feature.VIEW_BOOKS_BORROWED_BY_USER);
		return rentalService.getBooksBorrowedByUser(id);
	}
}