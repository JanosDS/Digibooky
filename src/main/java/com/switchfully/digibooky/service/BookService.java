package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.dto.author.AuthorDTO;
import com.switchfully.digibooky.dto.author.AuthorMapper;
import com.switchfully.digibooky.dto.book.*;
import com.switchfully.digibooky.dto.rental.RentalMapper;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.exception.InvalidIsbnException;
import com.switchfully.digibooky.exception.MandatoryFieldException;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookService {
	private final BookMapper bookMapper;
	private final BookRepository bookRepository;
	private final RentalRepository rentalRepository;
	private final AuthorMapper authorMapper;
	private final RentalMapper rentalMapper;

	public BookService(BookMapper bookMapper, BookRepository bookRepository, RentalMapper rentalMapper, RentalRepository rentalRepository, AuthorMapper authorMapper) {
		this.bookMapper = bookMapper;
		this.bookRepository = bookRepository;
		this.rentalMapper = rentalMapper;
		this.rentalRepository = rentalRepository;
		this.authorMapper = authorMapper;
	}

	public List<BookDTO> getAllBooks() {
		return bookMapper.mapToDTO(bookRepository.getAllBooks());
	}

	public BookDTO getBookByIsbn(String isbn) {
		return bookMapper.mapToDTO(bookRepository.getBookByIsbn(isbn)
				.orElseThrow(() -> new BookNotFoundException(isbn + " is not a valid isbn, book not found.")));
	}

	public List<BookDTO> getBookListByIsbn(String isbn) {
		if (isbn.contains("*")) {
			String isbnWithoutWildcard = isbn.replace("*", "");
			List<Book> bookListWithWildcard = bookRepository.getAllBooks().stream()
					.filter(book -> book.getIsbn().contains(isbnWithoutWildcard))
					.collect(Collectors.toList());
			return bookMapper.mapToDTO(bookListWithWildcard);
		}
		return List.of(getBookByIsbn(isbn));
	}

	public BookDTO createBook(CreateBookDTO newBook) {
		validateMandatoryFields(newBook);
		if (isUniqueIsbn(newBook.getIsbn())) {
			throw new InvalidIsbnException("Isbn is not unique");
		}
		return bookMapper.mapToDTO(bookRepository.addBook(bookMapper.mapToDomain(newBook)));
	}

	public BookDTO updateBook(BookUpdateDTO bookUpdateDTO) {
		Book bookToUpdate = bookRepository.getBookByIsbn(bookUpdateDTO.getIsbn())
				.orElseThrow(() -> new BookNotFoundException("Provided book is not present, so we cant update it."));

		bookToUpdate.setAvailable(bookUpdateDTO.isAvailable());
		bookToUpdate.setTitle(bookUpdateDTO.getTitle());
		bookToUpdate.setAuthorList(authorMapper.mapToDomain(bookUpdateDTO.getAuthorList()));
		bookToUpdate.setSummary(bookUpdateDTO.getSummary());
		return bookMapper.mapToDTO(bookRepository.updateBook(bookToUpdate));
	}

	public void validateMandatoryFields(CreateBookDTO newBook) {
		if (newBook.getIsbn() == null) {
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
		Rental rental = rentalRepository.getRentalByIsbn(isbn).orElse(null);
		if (Objects.isNull(rental)) {
			return bookMapper.mapToDetailDTO(bookRepository.getBookByIsbn(isbn)
					.orElseThrow(() -> new BookNotFoundException(isbn + " is not a valid isbn, book not found.")));
		}
		BookDetailDTO bookDetailDTO = bookMapper.mapToDetailDTO(bookRepository.getBookByIsbn(isbn)
				.orElseThrow(() -> new BookNotFoundException(isbn + " is not a valid isbn, book not found.")));
		bookDetailDTO.setRentalDTO(rentalMapper.mapToDTO(rental));
		return bookDetailDTO;
	}

	public boolean isUniqueIsbn(String isbn) {
		return bookRepository.getAllBooks()
				.stream()
				.noneMatch(book -> book.getIsbn().equals(isbn));
	}

	public List<BookDTO> getBooksByTitle(String title) {

		/** Implement wildcard logic **/

		return bookMapper.mapToDTO(bookRepository.getBooksByTitle(title));

	}

	public BookDTO deleteBook(String isbn) {
		Book bookToDelete = bookRepository.getBookByIsbn(isbn)
				.orElseThrow(() -> new BookNotFoundException(isbn + " is not a valid isbn, no book was found to delete."));
		return bookMapper.mapToDTO(bookRepository.deleteBook(bookToDelete));
	}

	public BookDTO unDeleteBook(String isbn) {
		Book bookToUnDelete = bookRepository.getDeletedBookByIsbn(isbn)
				.orElseThrow(() -> new BookNotFoundException(isbn + " is not a valid isbn, no book was found to undelete."));
		;
		return bookMapper.mapToDTO(bookRepository.unDeleteBook(bookToUnDelete));
	}

	public List<BookDTO> getBookByAuthor(String name) {
		List<BookDTO> bookDTOList = new ArrayList<>();
		//Wildcard logic

		//Name split logic
		String[] inputSplit = name.split("\\s+");

		for (String namePart : inputSplit) {
			bookDTOList.addAll(bookMapper.mapToDTO(bookRepository.getBookByAuthor(namePart)));
		}
		return bookDTOList;
	}
}

