package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.author.AuthorDTO;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.book.BookDetailDTO;
import com.switchfully.digibooky.dto.book.BookMapper;
import com.switchfully.digibooky.dto.book.CreateBookDTO;
import com.switchfully.digibooky.exception.MandatoryFieldException;
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
		bookRepository.addBook(bookToStore);
		return bookDTO;
	}

	public BookDTO createBook(CreateBookDTO newBook){
		validateMandatoryFields(newBook);
		return bookMapper.mapToDTO(bookRepository.addBook(bookMapper.mapToDomain(newBook)));
	}

	public BookDTO updateBook(BookDTO bookDTO) {
		Book bookToUpdate = bookMapper.mapToDomain(bookDTO);
		bookRepository.updateBook(bookToUpdate);
		return bookDTO;
	}

	public void validateMandatoryFields(CreateBookDTO newBook){
		if(newBook.getIsbn() == null){
			throw new MandatoryFieldException("The ISBN of the book can't be empty");
		}
		if (newBook.getTitle() == null) {
			throw new MandatoryFieldException("The title of the book can't be empty");
		}
		for (AuthorDTO authorsOfBooks :
				newBook.getAuthorList()) {
			if (authorsOfBooks.getLastName() == null) {
				throw new MandatoryFieldException("The last name of the author can't be empty");
			}
		}
	}

	public BookDetailDTO getBookDetailByIsbn(String isbn) {
		boolean wildcard = isbn.contains("*");
		if (!wildcard) {
			return getBookDetailById(isbn);
		}
	}

	private BookDetailDTO getBookDetailById(String isbn) {
		return bookDetailMapper.mapToDTO(bookRepository.getById(isbn),rentalRepository.getByIsbn(isbn));
	}
}

