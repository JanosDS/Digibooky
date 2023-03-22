package com.switchfully.digibooky.dto.book;

import com.switchfully.digibooky.domain.author.Author;
import com.switchfully.digibooky.dto.author.AuthorDto;

import java.util.List;

public class BookDTO {

	//isbn lowercase
	private final String isbn;
	private final String title;
	private final List<AuthorDto> authorList;

	public BookDTO(String isbn, String title, List<AuthorDto> authorList) {
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

	public List<AuthorDto> getAuthorList() {
		return authorList;
	}

}
