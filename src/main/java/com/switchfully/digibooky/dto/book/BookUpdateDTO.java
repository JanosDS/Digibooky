package com.switchfully.digibooky.dto.book;


import com.switchfully.digibooky.dto.author.AuthorDTO;

import java.util.List;
public class BookUpdateDTO {

    private final String isbn;
    private final String title;
    private final List<AuthorDTO> authorList;

    private final String summary;
    private final boolean isAvailable;

public BookUpdateDTO(String isbn, String title, List<AuthorDTO> authorList, String summary, boolean isAvailable) {
        this.title = title;
        this.authorList = authorList;
        this.summary = summary;
        this.isAvailable = isAvailable;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public List<AuthorDTO> getAuthorList() {
        return authorList;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getIsbn() {
        return isbn;
    }
}
