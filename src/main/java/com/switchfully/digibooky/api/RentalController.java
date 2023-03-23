package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.user.UserDTO;
import com.switchfully.digibooky.dto.user.UserMapper;
import com.switchfully.digibooky.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/rental")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService){
        this.rentalService = rentalService;
    }
    
    @PostMapping(consumes = "application/json", produces = "application/json", path = "/rent")
    public void rentBook(@RequestBody String title, @RequestBody UserDTO userDTO){
        rentalService.rentBook(title, userDTO);
    }

    @DeleteMapping(consumes = "application/json", produces = "application/json", path = "/return")
    public void returnBook(@RequestBody Rental rental){
        rentalService.returnBook(rental);
    }

    @GetMapping(consumes = "application/json", produces = "application/json", path = "/overdue")
    public List<BookDTO> getOverdueBooks(){
        return rentalService.getOverdueBooks();
    }
}