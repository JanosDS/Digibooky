package com.switchfully.digibooky.dto.book;

import com.switchfully.digibooky.dto.author.AuthorDTO;

import java.util.List;

public class BookDTO {

	private final String isbn;
	private final String title;
	private final List<AuthorDTO> authorList;

	public BookDTO(String isbn, String title, List<AuthorDTO> authorList) {
		this.isbn = isbn;
		this.title = title;
		this.authorList = authorList;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public List<AuthorDTO> getAuthorList() {
		return authorList;
	}

}
