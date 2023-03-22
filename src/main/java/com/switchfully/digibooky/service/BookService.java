package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.book.BookMapper;
import com.switchfully.digibooky.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	private final BookMapper bookMapper;
	private final BookRepository bookRepository;

	public BookService(BookMapper bookMapper, BookRepository bookRepository) {
		this.bookMapper = bookMapper;
		this.bookRepository = bookRepository;
	}
}