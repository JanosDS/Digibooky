package com.switchfully.digibooky.dto.book;

import com.switchfully.digibooky.domain.author.Author;

import java.util.List;

public class BookDTO {
	private final String ISBN;
	private  String title;
	private  List<Author> authorList;

	private  String summary;
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

	public void setAvailable(boolean available) {
		isAvailable = available;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public void setAuthorList(List<Author> authorList) {
		this.authorList = authorList;
	}

}
