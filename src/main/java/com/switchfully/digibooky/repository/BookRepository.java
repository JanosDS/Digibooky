package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Repository
public class BookRepository {

	private List<Book> bookList;

	public BookRepository() {
		this.bookList = new ArrayList<>();
//        Add books in list for testing purpose
		bookList.add(new Book("1234", "Once upon a time", "Summary", true, List.of(new Author("Rohan", "Thys"), new Author("Jean", "David"))));
		bookList.add(new Book("9876", "Test", "Summary", true, List.of(new Author("Rohan", "Thys"))));
	}

	public Book getById(String isbn) {
		return bookList.stream()
				.filter(book -> book.getIsbn().equals(isbn))
				.findFirst()
				.orElseThrow();
	}

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

	public List<Book> getBookByAuthor(String name) {
		return bookList.stream()
				.filter(book -> book.isBookWrittenBy(name))
				.collect(Collectors.toList());
	}


}

