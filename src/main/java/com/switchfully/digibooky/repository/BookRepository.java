package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

	private List<Book> bookList;

	public BookRepository() {
		this.bookList = new ArrayList<>();
		List<Author> authorList = List.of(new Author("Jimmy", "Sirius"));
		Book titleTest1 = new Book("randomISBN12345", "Match", "summary1", true, authorList);
		Book titleTest2 = new Book("randomISBN12345", "Match me", "summary1", true, authorList);
		addBook(titleTest2);
		addBook(titleTest1);
	}

	public Book getById(String isbn) {
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

