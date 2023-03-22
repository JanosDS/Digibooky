package com.switchfully.digibooky.repository;
import com.switchfully.digibooky.domain.book.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

	private final List<Book> bookList;

	public BookRepository() {
		this.bookList = new ArrayList<>();
	}

	public List<Book> getAllBooks() {
		return bookList;
	}

	//for testing purposes
	public void putBookInList(Book book){
		bookList.add(book);
	}
}
