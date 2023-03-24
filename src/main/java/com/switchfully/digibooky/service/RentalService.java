package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.book.BookMapper;
import com.switchfully.digibooky.dto.rental.CreateRentalDTO;
import com.switchfully.digibooky.dto.rental.RentalDTO;
import com.switchfully.digibooky.dto.rental.RentalMapper;
import com.switchfully.digibooky.dto.user.UserDTO;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.exception.BookUnavailableException;
import com.switchfully.digibooky.exception.RentalNotFoundException;
import com.switchfully.digibooky.exception.UserNotFoundException;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.RentalRepository;
import com.switchfully.digibooky.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RentalService {
	private final RentalRepository rentalRepository;
	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	private final BookMapper bookMapper;
	private final RentalMapper rentalMapper;

	@Autowired
	public RentalService(RentalRepository rentalRepository, BookRepository bookRepository, UserRepository userRepository, BookMapper bookMapper, RentalMapper rentalMapper) {
		this.rentalRepository = rentalRepository;
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
		this.bookMapper = bookMapper;
		this.rentalMapper = rentalMapper;
	}

	public RentalDTO rentBook(CreateRentalDTO createRentalDTO) {

		UUID uuid = createRentalDTO.getUserId();

		Book book = bookRepository.getBookByIsbn(createRentalDTO.getIsbn())
				.orElseThrow(() -> new BookNotFoundException(createRentalDTO.getIsbn() + "Is not a valid isbn, book not found"));
		Rental rental;
		if (book.isAvailable()) {
			rental = new Rental(bookRepository.getBookByIsbn(createRentalDTO.getIsbn())
					        .orElseThrow(() -> new BookNotFoundException(createRentalDTO.getIsbn() + "Is not a valid isbn, book not found")),
					userRepository.getUserByUuid(uuid)
							.orElseThrow(() -> new UserNotFoundException("User not found")));
			rentalRepository.addRental(rental);
			book.setAvailable(false);
			return rentalMapper.mapToDTO(rental);
		}
		throw new BookUnavailableException("This book is already rented out.");
	}

	public RentalDTO returnBook(String rentalId) {
		Rental rental = rentalRepository.getById(rentalId)
				.orElseThrow(() -> new RentalNotFoundException("No valid rental id provided."));
		String isbn = rental.getBook().getIsbn();
		Book book = bookRepository.getBookByIsbn(isbn).orElseThrow(()-> new BookNotFoundException(isbn + "Is not a valid isbn, book not found"));
		UUID userId = rental.getUser().getUserId();
		User user = userRepository.getUserByUuid(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
		if (rental.getBook().getIsbn().equals(isbn) && rental.getUser().getUserId().equals(user.getUserId())) {
			if (rental.getDueDate().isBefore(LocalDate.now())) {
				throw new IllegalArgumentException("Book is overdue");
			}
			rentalRepository.removeRental(rental);
			book.setAvailable(true);
		} else {
			//make custom exception
			throw new IllegalArgumentException("Book is not rented by this user");
		}
		return rentalMapper.mapToDTO(rental);
	}

	public List<BookDTO> getOverdueBooks() {
		return rentalRepository.getOverdueBooks().stream()
				.map(rental -> bookRepository.getBookByIsbn(rental.getBook().getIsbn())
                        .orElseThrow(()-> new BookNotFoundException("book not found")))
				.map(bookMapper::mapToDTO)
				.collect(Collectors.toList());
	}

	public List<RentalDTO> getRentalsByUserId(UUID userId) {
		List<Rental> rentalsByUserId = rentalRepository.getRentalsByUserId(userId);
		return rentalMapper.mapToDTO(rentalsByUserId);
	}

	public List<BookDTO> getBooksBorrowedByUser(String userId) {
		UUID uuid;
		try {
			uuid = UUID.fromString(userId);
		} catch (IllegalArgumentException exception) {
			throw new UserNotFoundException(userId + " is not a valid user");
		}
		List<Book> booksByUser = getRentalsByUserId(uuid).stream()
				.map(rentalDTO -> bookRepository.getBookByIsbn(rentalDTO.getBookDTO().getIsbn())
                        .orElseThrow(()-> new BookNotFoundException("book not found")))
				.toList();
		return bookMapper.mapToDTO(booksByUser);
	}

}