package com.switchfully.digibooky.domain.book;

import com.switchfully.digibooky.domain.author.Author;

import java.util.List;

public class Book {
    private final String isbn;
    private final String title;
    private final List<Author> authorList;

    private final String summary;
    private boolean isAvailable;

    public Book(String isbn, String title, List<Author> authorList, String summary, boolean isAvailable) {
        this.isbn = isbn;
        this.title = title;
        this.authorList = authorList;
        this.summary = summary;
        this.isAvailable = isAvailable;
    }

    private String getBookDetails() {
        return " ";
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

    public List<Author> getAuthorList() {
        return authorList;
    }

}

