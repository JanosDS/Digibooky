package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController()
@RequestMapping("/rental")
public class RentalController {

    private RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService){
        this.rentalService = rentalService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public void rentBook(@RequestBody String title, @RequestBody User user){
        rentalService.rentBook(title, user);
    }

  @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(consumes = "application/json", produces = "application/json")
    public void returnBook(@RequestBody Rental rental){
        rentalService.returnBook(rental);

    }

}