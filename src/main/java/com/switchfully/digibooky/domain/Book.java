package com.switchfully.digibooky.domain;

import java.util.List;

public class Book {
	private final String isbn;
	private String title;
	private List<Author> authorList;

	private String summary;
	private boolean isAvailable;

	public Book(String isbn, String title, String summary, boolean isAvailable, List<Author> authorList) {
		this.isbn = isbn;
		this.title = title;
		this.summary = summary;
		this.isAvailable = isAvailable;
		this.authorList = authorList;
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

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthorList(List<Author> authorList) {
		this.authorList = authorList;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public boolean isBookWrittenBy(String authorName){
		return authorList.stream()
				.anyMatch(author -> author.getFirstName().toLowerCase().contains(authorName.toLowerCase()) || author.getLastName().toLowerCase().contains(authorName.toLowerCase()));
	}
}
