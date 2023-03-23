package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class BookRepository {

	private final List<Book> bookList;

	public BookRepository() {
		this.bookList = new ArrayList<>();
	}

	public Book getById(String Isbn) {
		return bookList.stream()
				.filter(book -> book.getISBN().equals(Isbn))
				.findFirst()
				.orElse(null);
	}
	public void putBookInList(Book bookToStore1) {
		bookList.add(bookToStore1);
	}
	public List getAllBooks() {
		return bookList;
	}


	public List<Book> getBookList() {
		return bookList;
	}
	public void updateBook(Book bookToUpdate) {
		bookList.remove(bookToUpdate);
		bookList.add(bookToUpdate);
	}

	public Book getBookByTitle(String title) {
		return bookList.stream()
				.filter(book -> book.getTitle().equals(title))
				.findFirst()
				.orElse(null);
	}

	public void removeBook(Book bookToRemove) {
		bookList.remove(bookToRemove);
	}
	public void addBook(Book bookToAdd) {
		bookList.add(bookToAdd);
	}
}

