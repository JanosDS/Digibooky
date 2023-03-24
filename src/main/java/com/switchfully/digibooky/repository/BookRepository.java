package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Author;
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

	public Book getByIsbn(String isbn) {
		return bookList.stream()
				.filter(book -> book.getIsbn().equals(isbn))
				.findFirst()
				.orElseThrow();
	}
//	public Book addBook(Book bookToStore1) {
//		bookList.add(bookToStore1);
//		return bookToStore1;
//	}
	public List<Book> getAllBooks() {
		return bookList;
	}
	
	public void updateBook(Book updatedBook,String isbn) {
		Book bookToUpdate = bookList.stream().filter(book -> book.getIsbn().equals(isbn))
				.findFirst()
				.orElse(null);
		bookList.set(bookList.indexOf(bookToUpdate),updatedBook);
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
	public Book addBook(Book bookToAdd) {
		bookList.add(bookToAdd);
		return bookToAdd;
	}

	public Book getByTitle(String title) {
		return bookList.stream()
				.filter(book -> book.getTitle().contains(title))
				.findFirst()
				.orElse(null);
	}
}

