package com.switchfully.digibooky.domain;

import java.util.List;

import com.switchfully.digibooky.domain.author.Author;

import java.util.List;

public class Book {
<<<<<<< HEAD:src/main/java/com/switchfully/digibooky/domain/Book.java
	private final String ISBN;
	private final String title;
	private final List<Author> authorList;
=======
    private final String ISBN;
    private final String title;
    private final List<Author> authorList;
>>>>>>> JimmyBranch:src/main/java/com/switchfully/digibooky/domain/book/Book.java

    private final String summary;
    private boolean isAvailable;

<<<<<<< HEAD:src/main/java/com/switchfully/digibooky/domain/Book.java
	public Book(String ISBN, String title, String summary, boolean isAvailable, List<Author> authorList) {
		this.ISBN = ISBN;
		this.title = title;
		this.summary = summary;
		this.isAvailable = isAvailable;
		this.authorList = authorList;
	}
=======
    public Book(String ISBN, String title, List<Author> authorList, String summary, boolean isAvailable) {
        this.ISBN = ISBN;
        this.title = title;
        this.authorList = authorList;
        this.summary = summary;
        this.isAvailable = isAvailable;
    }
>>>>>>> JimmyBranch:src/main/java/com/switchfully/digibooky/domain/book/Book.java

    private String getBookDetails() {
        return " ";
    }

    public String getIsbn() {
        return ISBN;
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

