package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.dto.author.AuthorDTO;
import com.switchfully.digibooky.dto.author.AuthorMapper;
import com.switchfully.digibooky.dto.book.*;
import com.switchfully.digibooky.exception.InvalidIsbnException;
import com.switchfully.digibooky.exception.MandatoryFieldException;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.RentalRepository;
import org.springframework.stereotype.Service;

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

    public BookDTO getBookByIsbn(String isbn) {
        return bookMapper.mapToDTO(bookRepository.getByIsbn(isbn));
    }

	public List<BookDTO> getBooksByIsbn(String isbn) {
		if (isbn.contains("*")) {
			String isbnWithoutWildcard = isbn.replace("*", "");
			List<Book> bookListWithWildcard = bookRepository.getAllBooks().stream().filter(book -> book.getIsbn().contains(isbnWithoutWildcard)).collect(Collectors.toList());
			return bookMapper.mapToDTO(bookListWithWildcard);
		}
		return List.of(getBookByIsbn(isbn));
	}


	public BookDTO getBookById(String id) {
		return bookMapper.mapToDTO(bookRepository.getByIsbn(id));
	}

	public BookDTO createBook(CreateBookDTO newBook) {
		validateMandatoryFields(newBook);
		if (isUniqueIsbn(newBook.getIsbn())) {
			throw new InvalidIsbnException("Isbn is not unique");
		}
		return bookMapper.mapToDTO(bookRepository.addBook(bookMapper.mapToDomain(newBook)));
	}

	public BookDTO updateBook(BookUpdateDTO bookUpdateDTO, String isbn) {
		Book bookToUpdate = bookRepository.getByIsbn(isbn);

		bookToUpdate.setAvailable(bookUpdateDTO.isAvailable());
		bookToUpdate.setTitle(bookUpdateDTO.getTitle());
		bookToUpdate.setAuthorList(authorMapper.mapToDomain(bookUpdateDTO.getAuthorList()));
		bookToUpdate.setSummary(bookUpdateDTO.getSummary());
		bookRepository.updateBook(bookToUpdate, isbn);
		return bookMapper.mapToDTO(bookToUpdate);
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
		Rental rental = rentalRepository.getByIsbn(isbn);
		if (rental == null) {
			return bookDetailMapper.mapToDTO(bookRepository.getByIsbn(isbn));
		}
		BookDetailDTO bookDetailDTO = bookDetailMapper.mapToDTO(bookRepository.getByIsbn(isbn));
		bookDetailDTO.setUserId(rental.getUserId());
		return bookDetailDTO;
	}

	public boolean isUniqueIsbn(String isbn) {
		return bookRepository.getAllBooks()
				.stream()
				.noneMatch(book -> book.getIsbn().equals(isbn));
	}

	public List<BookDTO> getBooksByTitle(String title) {

		/** Implement wildcard logic **/

		if (title == null) {
			throw new NullPointerException();
		}
		List<Book> bookList = bookRepository.getAllBooks();
		return bookList.stream()
				.filter(book -> (book.getTitle().toLowerCase()).contains(title.toLowerCase()))
				.map(bookMapper::mapToDTO)
				.collect(Collectors.toList());
	}

	public BookDTO deleteBook(String id) {
		Book bookToDelete = bookRepository.getById(id);
		bookRepository.deleteBook(bookToDelete);
		return bookMapper.mapToDTO(bookToDelete);
	}

	public BookDTO unDeleteBook(String id) {
		Book bookToUnDelete = bookRepository.getDeletedBookById(id);
		bookRepository.unDeleteBook(bookToUnDelete);
		return bookMapper.mapToDTO(bookToUnDelete);
	}
}

