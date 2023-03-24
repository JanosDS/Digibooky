package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

	private List<Book> bookList;

	private List<Book> deletedBooks;
	public BookRepository() {
		this.bookList = new ArrayList<>();
		this.deletedBooks = new ArrayList<>();
	}

	public Book getByIsbn(String isbn) {
		return bookList.stream()
				.filter(book -> book.getIsbn().equals(isbn))
				.findFirst()
				.orElseThrow();
	}

	public Book getDeletedBookById(String isbn) {
		return deletedBooks.stream()
				.filter(book -> book.getIsbn().equals(isbn))
				.findFirst()
				.orElse(null);
	}
	public void putBookInList(Book bookToStore1) {
		bookList.add(bookToStore1);
	}

	public void removeBook(Book bookToRemove) {
		bookList.remove(bookToRemove);
	}
	public Book addBook(Book bookToAdd) {
		bookList.add(bookToAdd);
		return bookToAdd;
	}


	public List<Book> getBookList() {
		return bookList;
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
				.filter(book -> book.getTitle().contains(title))
				.findFirst()
				.orElse(null);
	}

	public List<Book> getDeletedBooks() {
		return deletedBooks;
	}

    public void deleteBook(Book bookToDelete) {
		bookList.remove(bookToDelete);
		deletedBooks.add(bookToDelete);
    }

	public void unDeleteBook(Book bookToUnDelete) {
		deletedBooks.remove(bookToUnDelete);
		bookList.add(bookToUnDelete);
	}
}

