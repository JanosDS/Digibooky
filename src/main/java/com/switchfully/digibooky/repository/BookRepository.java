package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

	private List<Book> bookList;

	public BookRepository() {
		this.bookList = new ArrayList<>();
	}

	public Book getById(String Isbn) {
		return bookList.stream()
				.filter(book -> book.getISBN().equals(Isbn))
				.findFirst()
				.orElse(null);
	}
	public Book addBook(Book bookToStore1) {
		bookList.add(bookToStore1);
		return bookToStore1;
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
}

