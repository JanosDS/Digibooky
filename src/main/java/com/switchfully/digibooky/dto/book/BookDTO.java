package com.switchfully.digibooky.dto.book;

import com.switchfully.digibooky.domain.author.Author;

import java.util.ArrayList;
import java.util.List;

public class BookDTO {
	private final String ISBN;
	private final String title;

	private final List<Author> authorList;

	public BookDTO(String ISBN, String title, List<Author> authorList) {
		this.ISBN = ISBN;
		this.title = title;
		this.authorList = authorList;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getTitle() {
		return title;
	}

	public List<Author> getAuthorList() {
		return authorList;
	}

}
