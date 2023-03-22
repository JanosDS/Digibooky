package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.book.Book;
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

	public Book getById(String ISBN) {
		return bookList.stream()
				.filter(book -> book.getISBN().equals(ISBN))
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
}


