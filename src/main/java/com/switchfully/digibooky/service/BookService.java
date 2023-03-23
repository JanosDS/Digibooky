package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.dto.author.AuthorDTO;
import com.switchfully.digibooky.dto.author.AuthorMapper;
import com.switchfully.digibooky.dto.book.*;
import com.switchfully.digibooky.exception.MandatoryFieldException;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.RentalRepository;
import org.springframework.stereotype.Service;
import com.switchfully.digibooky.domain.Book;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
	private final BookMapper bookMapper;
	private final BookRepository bookRepository;
	private final BookDetailMapper bookDetailMapper;
	private final RentalRepository rentalRepository;
	private final AuthorMapper authorMapper;

	public BookService(BookMapper bookMapper, BookRepository bookRepository, BookDetailMapper bookDetailMapper, RentalRepository rentalRepository, AuthorMapper authorMapper) {
		this.bookMapper = bookMapper;
		this.bookRepository = bookRepository;
		this.bookDetailMapper = bookDetailMapper;
		this.rentalRepository = rentalRepository;
		this.authorMapper = authorMapper;
	}
	public List<BookDTO> getAllBooks() {
		return bookMapper.mapToDTO(bookRepository.getAllBooks());

	}


	public BookDTO getBookById(String id) {
		return bookMapper.mapToDTO(bookRepository.getById(id));
	}
	public List<BookDTO> getBooksByIsbn(String isbn) {
		boolean wildcard = isbn.contains("*");
		if (!wildcard) {
			return List.of(getBookById(isbn));
		} else {
			String isbnWithoutWildcard = isbn.replace("*", "");
			List<Book> bookList = bookRepository.getAllBooks();
			List<Book> bookListWithWildcard = bookList.stream()
					.filter(book -> book.getIsbn().contains(isbnWithoutWildcard))
					.collect(Collectors.toList());
			return bookMapper.mapToDTO(bookListWithWildcard);
		}
	}


	public BookDTO createBook(CreateBookDTO newBook){
		validateMandatoryFields(newBook);
		
		return bookMapper.mapToDTO(bookRepository.addBook(bookMapper.mapToDomain(newBook)));
	}

	public BookDTO updateBook(BookUpdateDTO bookUpdateDTO,String isbn) {
		Book bookToUpdate = bookRepository.getById(isbn);
		bookToUpdate.setAvailable(bookUpdateDTO.isAvailable());
		bookToUpdate.setTitle(bookUpdateDTO.getTitle());
		bookToUpdate.setAuthorList(authorMapper.mapToDomain(bookUpdateDTO.getAuthorList()));
		bookToUpdate.setSummary(bookUpdateDTO.getSummary());
		bookRepository.updateBook(bookToUpdate,isbn);
		return bookMapper.mapToDTO(bookToUpdate);
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
		//TODO: implement the return in case this contains a wildcard
		return null;
	}

	private BookDetailDTO getBookDetailById(String isbn) {
		Rental rental = rentalRepository.getByIsbn(isbn);
		if (rental == null){
			return bookDetailMapper.mapToDTO(bookRepository.getById(isbn));
		}
		BookDetailDTO bookDetailDTO = bookDetailMapper.mapToDTO(bookRepository.getById(isbn));
		bookDetailDTO.setUserId(rental.getUserId());
		return  bookDetailDTO;
	}

	public boolean isUniqueIsbn(String isbn) {
		return bookRepository.getAllBooks()
				.stream()
				.noneMatch(book -> book.getIsbn().equals(isbn));
	}
}

