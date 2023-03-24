package com.switchfully.digibooky.dto.rental;

import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.user.UserDTO;

import java.time.LocalDate;
import java.util.UUID;

public class RentalDTO {

    private final UserDTO userDTO;
    private final BookDTO bookDTO;
    private final UUID rentalId;
    private final LocalDate dueDate;

    public RentalDTO(BookDTO bookDTO, UserDTO userDTO, UUID rentalId, LocalDate dueDate) {
        this.bookDTO = bookDTO;
        this.userDTO = userDTO;
        this.rentalId = rentalId;
        this.dueDate = dueDate;
    }
    public UUID getRentalId() {
        return rentalId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }
}