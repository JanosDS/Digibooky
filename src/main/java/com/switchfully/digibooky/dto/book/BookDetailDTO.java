package com.switchfully.digibooky.dto.book;

import com.switchfully.digibooky.dto.author.AuthorDTO;
import com.switchfully.digibooky.dto.rental.RentalDTO;

import java.util.List;
import java.util.UUID;

public class BookDetailDTO {
    private final String isbn;
    private final String title;
    private final List<AuthorDTO> authorList;
    private final String summary;
    private final boolean isAvailable;
    private RentalDTO rentalDTO;


    public BookDetailDTO(String isbn, String title, List<AuthorDTO> authorList, String summary, boolean isAvailable) {
        this.isbn = isbn;
        this.title = title;
        this.authorList = authorList;
        this.summary = summary;
        this.isAvailable = isAvailable;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public List<AuthorDTO> getAuthorList() {
        return authorList;

    }

    public RentalDTO getRentalDTO() {
        return rentalDTO;
    }

    public void setRentalDTO(RentalDTO rentalDTO) {
        this.rentalDTO = rentalDTO;
    }
}
