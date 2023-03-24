package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.domain.user.Feature;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.user.UserDTO;
import com.switchfully.digibooky.service.RentalService;
import com.switchfully.digibooky.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/rental")
public class RentalController {

    private final RentalService rentalService;
    private final SecurityService securityService;
    @Autowired
    public RentalController(RentalService rentalService, SecurityService securityService) {
        this.rentalService = rentalService;
        this.securityService = securityService;
    }
    @GetMapping(produces = "application/json", path = "/overdue")
    public List<BookDTO> getOverdueBooks() {
        return rentalService.getOverdueBooks();
    }

    @GetMapping(produces = "application/json", path = "/{id}")
    public List<BookDTO> getBooksBorrowedByUser (@RequestHeader String authorization, @PathVariable String id) {
        securityService.validateAuthorization(authorization, Feature.VIEW_BOOKS_BORROWED_BY_USER);
        return rentalService.getBooksBorrowedByUser(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public void rentBook(@RequestBody String title, @RequestBody UserDTO userDTO) {
        rentalService.rentBook(title, userDTO);
    }

    @DeleteMapping(consumes = "application/json", produces = "application/json")
    public void returnBook(@RequestBody Rental rental) {
        rentalService.returnBook(rental);
    }

}