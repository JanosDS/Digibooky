package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.book.BookMapper;
import com.switchfully.digibooky.repository.BookRepository;
import org.springframework.stereotype.Service;
import com.switchfully.digibooky.domain.Book;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
	private final BookMapper bookMapper;
	private final BookRepository bookRepository;

	public BookService(BookMapper bookMapper, BookRepository bookRepository) {
		this.bookMapper = bookMapper;
		this.bookRepository = bookRepository;
	}
	public List<BookDTO> getAllBooks() {
		return bookMapper.mapToDTO(bookRepository.getAllBooks());

	}


	public BookDTO getBookById(String id) {
		return bookMapper.mapToDTO(bookRepository.getById(id));
	}
	public List<BookDTO> getBooksByIsbn(String Isbn) {
		boolean wildcard = Isbn.contains("*");
		if (!wildcard) {
			return List.of(getBookById(Isbn));
		} else {
			String ISBNWithoutWildcard = Isbn.replace("*", "");
			List<Book> bookList = bookRepository.getAllBooks();
			List<Book> bookListWithWildcard = bookList.stream()
					.filter(book -> book.getISBN().contains(ISBNWithoutWildcard))
					.collect(Collectors.toList());
			return bookMapper.mapToDTO(bookListWithWildcard);
		}
	}

	public BookDTO createBook(BookDTO bookDTO) {
		Book bookToStore = bookMapper.mapToDomain(bookDTO);
		bookRepository.putBookInList(bookToStore);
		return bookDTO;
	}

	public BookDTO updateBook(BookDTO bookDTO) {
		Book bookToUpdate = bookMapper.mapToDomain(bookDTO);
		bookRepository.updateBook(bookToUpdate);
		return bookDTO;
	}

    public BookDTO deleteBook(String id) {
		Book bookToDelete = bookRepository.getById(id);
		bookRepository.deleteBook(bookToDelete);
		return bookMapper.mapToDTO(bookToDelete);
    }
}

