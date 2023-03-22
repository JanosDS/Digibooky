package com.switchfully.digibooky.dto.book;

import com.switchfully.digibooky.domain.author.Author;

import java.util.List;

public class BookDTO {
	private final String ISBN;
	private final String title;
	private final List<Author> authorList;

	private final String summary;
	private boolean isAvailable;

	public BookDTO(String ISBN, String title, List<Author> authorList, String summary, boolean isAvailable) {
		this.ISBN = ISBN;
		this.title = title;
		this.authorList = authorList;
		this.summary = summary;
		this.isAvailable = isAvailable;
	}
	public String getISBN() {
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
