package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

	private List<Book> bookList;

	private List<Book> deletedBooks;

	public BookRepository() {
		this.bookList = new ArrayList<>();
		this.deletedBooks = new ArrayList<>();
//        Add books in list for testing purpose
		bookList.add(new Book("1234", "Once upon a time", "Summary", true, List.of(new Author("Rohan", "Thys"), new Author("Jean", "David"))));
		bookList.add(new Book("9876", "Test", "Summary", true, List.of(new Author("Rohan", "Thys"))));
	}

	public Optional<Book> getBookByIsbn(String isbn) {
		return bookList.stream()
				.filter(book -> book.getIsbn().equals(isbn))
				.findFirst();
	}

	public Optional<Book> getDeletedBookByIsbn(String isbn) {
		return deletedBooks.stream()
				.filter(book -> book.getIsbn().equals(isbn))
				.findFirst();
	}

	public Book addBook(Book bookToAdd) {
		bookList.add(bookToAdd);
		return bookToAdd;
	}

	public List<Book> getAllBooks() {
		return bookList;
	}

	public Book updateBook(Book updatedBook) {
		bookList.set(bookList.indexOf(updatedBook), updatedBook);
		return updatedBook;
	}

	public List<Book> getBooksByTitle(String title) {
		return bookList.stream()
				.filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
				.collect(Collectors.toList());
	}

	public List<Book> getDeletedBooks() {
		return deletedBooks;
	}

	public Book deleteBook(Book bookToDelete) {
		bookList.remove(bookToDelete);
		deletedBooks.add(bookToDelete);
		return bookToDelete;
	}

	public Book unDeleteBook(Book bookToUnDelete) {
		deletedBooks.remove(bookToUnDelete);
		bookList.add(bookToUnDelete);
		return bookToUnDelete;
	}

	public List<Book> getBookByAuthor(String name) {
		return bookList.stream()
				.filter(book -> book.isBookWrittenBy(name))
				.collect(Collectors.toList());
	}

}

