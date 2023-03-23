package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class BookRepository {

	private List<Book> bookList;

	private List<Book> deletedBooks;
	public BookRepository() {
		this.bookList = new ArrayList<>();
		this.deletedBooks = new ArrayList<>();
	}

	public Book getById(String Isbn) {
		return bookList.stream()
				.filter(book -> book.getISBN().equals(Isbn))
				.findFirst()
				.orElse(null);
	}

	public Book getDeletedBookById(String Isbn) {
		return deletedBooks.stream()
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

	public List<Book> getDeletedBooks() {
		return deletedBooks;
	}

	public void updateBook(Book bookToUpdate) {
		bookList.remove(bookToUpdate);
		bookList.add(bookToUpdate);
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

