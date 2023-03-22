package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

	private final List<Book> bookList;

	public BookRepository() {
		this.bookList = new ArrayList<>();
	}
}
