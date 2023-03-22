package com.switchfully.digibooky.dto.book;

public class BookDTO {
	private final String ISBN;
	private final String title;
	//private final List<Author> authorList;

	private final String summary;
	private boolean isAvailable;

	public BookDTO(String ISBN, String title, String summary, boolean isAvailable) {
		this.ISBN = ISBN;
		this.title = title;
		this.summary = summary;
		this.isAvailable = isAvailable;
	}


}
