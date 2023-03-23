package com.switchfully.digibooky.dto.book;

import com.switchfully.digibooky.dto.author.AuthorDTO;

import java.util.List;

public class BookDTO {
    private final String isbn;
    private String title;
    private List<AuthorDTO> authorList;
    private String summary;
    private boolean isAvailable;

    public BookDTO(String isbn, String title, List<AuthorDTO> authorList, String summary, boolean isAvailable) {
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

}
