package com.switchfully.digibooky.dto.book;

import com.switchfully.digibooky.domain.Author;

import java.util.List;
public class BookUpdateDTO {

    private final String title;
    private final List<Author> authorList;

    private final String summary;
    private boolean isAvailable;

public BookUpdateDTO(String title, List<Author> authorList, String summary, boolean isAvailable) {
        this.title = title;
        this.authorList = authorList;
        this.summary = summary;
        this.isAvailable = isAvailable;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
