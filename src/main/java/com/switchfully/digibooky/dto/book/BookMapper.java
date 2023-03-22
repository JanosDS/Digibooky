package com.switchfully.digibooky.dto.book;

import com.switchfully.digibooky.domain.book.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

	public List<BookDTO> mapToDTO(List<Book> bookList){
		return bookList.stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	public BookDTO mapToDTO(Book book){
		return new BookDTO(book.getISBN(), book.getTitle(),book.getAuthorList(), book.getSummary(), book.isAvailable());
	}

    public Book mapToDomain(BookDTO bookDTO) {
		return new Book(bookDTO.getISBN(), bookDTO.getTitle(), bookDTO.getAuthorList(), bookDTO.getSummary(), bookDTO.isAvailable());
    }
}
