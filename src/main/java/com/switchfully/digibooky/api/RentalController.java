package com.switchfully.digibooky.api;

import com.switchfully.digibooky.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/rental")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService){
        this.rentalService = rentalService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public void rentBook(@RequestBody String title, @RequestBody String lastName, @RequestBody String firstName){
        rentalService.rentBook(title, lastName, firstName);
    }
}