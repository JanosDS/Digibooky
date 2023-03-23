package com.switchfully.digibooky.dto.book;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.dto.author.AuthorDTO;

import java.util.List;
public class BookUpdateDTO {

    private final String title;
    private final List<AuthorDTO> authorList;

    private final String summary;
    private boolean isAvailable;

public BookUpdateDTO(String title, List<AuthorDTO> authorList, String summary, boolean isAvailable) {
        this.title = title;
        this.authorList = authorList;
        this.summary = summary;
        this.isAvailable = isAvailable;
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
}
