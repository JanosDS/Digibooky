package com.switchfully.digibooky.dto.book;

import com.switchfully.digibooky.domain.book.Book;
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

	private BookDTO mapToDTO(Book book){
		return new BookDTO(
				book.getISBN(),
				book.getTitle(),
				authorMapper.mapToDTO(book.getAuthorList()));
	}
}
