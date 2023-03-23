package com.switchfully.digibooky.dto.book;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.dto.author.AuthorMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

	private AuthorMapper authorMapper;

	public BookMapper(AuthorMapper authorMapper) {
		this.authorMapper = authorMapper;
	}

	public List<BookDTO> mapToDTO(List<Book> bookList){
		return bookList.stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	public BookDTO mapToDTO(Book book){
		return new BookDTO(
				book.getISBN(),
				book.getTitle(),
				authorMapper.mapToDTO(book.getAuthorList()),
				book.getSummary(),
				book.isAvailable());
	}


	public Book mapToDomain(BookDTO bookDTO) {
		return new Book(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getSummary(), bookDTO.isAvailable(), authorMapper.mapToDomain(bookDTO.getAuthorList()));
	}

	public Book mapToDomain(CreateBookDTO createBookDTO){
		return new Book(createBookDTO.getIsbn(), createBookDTO.getTitle(), createBookDTO.getSummary(), createBookDTO.isAvailable(), authorMapper.mapToDomain(createBookDTO.getAuthorList()));
	}
}
