package com.switchfully.digibooky.dto.rental;


import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.dto.book.BookMapper;
import com.switchfully.digibooky.dto.user.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalMapper {

	private final BookMapper bookMapper;
	private final UserMapper userMapper;

	public RentalMapper(BookMapper bookMapper, UserMapper userMapper) {
		this.bookMapper = bookMapper;
		this.userMapper = userMapper;
	}

	public RentalDTO mapToDTO(Rental rental) {
		return new RentalDTO(bookMapper.mapToDTO(rental.getBook()), userMapper.mapToDTO(rental.getUser()), rental.getRentalId(), rental.getDueDate());
	}


	public List<RentalDTO> mapToDTO(List<Rental> rentalList) {
		return rentalList.stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

}