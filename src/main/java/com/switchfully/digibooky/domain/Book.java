package com.switchfully.digibooky.domain;

public class Book {
	private final String ISBN;
	private final String title;
	//private final List<Author> authorList;

	private final String summary;
	private boolean isAvailable;

	public Book(String ISBN, String title, String summary, boolean isAvailable) {
		this.ISBN = ISBN;
		this.title = title;
		this.summary = summary;
		this.isAvailable = isAvailable;
	}

	private String getBookDetails(){
		return " ";
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
}
