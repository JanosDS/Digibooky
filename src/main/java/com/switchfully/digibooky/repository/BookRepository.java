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

	public Book getById(String isbn) {
		return bookList.stream()
				.filter(book -> book.getIsbn().equals(isbn))
				.findFirst()
				.orElse(null);
	}
	public Book addBook(Book bookToStore1) {
		bookList.add(bookToStore1);
		return bookToStore1;
	}
	public List<Book> getAllBooks() {
		return bookList;
	}
	
	public void updateBook(Book bookToUpdate,String id) {
		bookList.remove(getById(id));
		bookList.add(bookToUpdate);
	}

	public Book getBookByTitle(String title) {
		return bookList.stream()
				.filter(book -> book.getTitle().equals(title))
				.findFirst()
				.orElse(null);
	}
}

