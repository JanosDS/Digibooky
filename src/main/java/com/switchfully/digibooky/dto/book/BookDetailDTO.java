package com.switchfully.digibooky.dto.book;

import com.switchfully.digibooky.dto.author.AuthorDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class BookDetailDTO {
    private final String isbn;
    private String title;
    private List<AuthorDTO> authorList;

    private String summary;
    private boolean isAvailable;

    private UUID userId;
    private UUID rentalId;
    private LocalDate dueDate;

    public BookDetailDTO(String isbn, String title, List<AuthorDTO> authorList, String summary, boolean isAvailable, UUID userId, UUID rentalId, LocalDate dueDate) {
        this.isbn = isbn;
        this.title = title;
        this.authorList = authorList;
        this.summary = summary;
        this.isAvailable = isAvailable;
        this.userId = userId;
        this.rentalId = rentalId;
        this.dueDate = dueDate;
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
    public UUID getUserId() {
        return userId;
    }

    public UUID getRentalId() {
        return rentalId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
