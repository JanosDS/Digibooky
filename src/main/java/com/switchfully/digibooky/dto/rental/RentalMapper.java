package com.switchfully.digibooky.dto.rental;


import com.switchfully.digibooky.domain.Rental;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalMapper {

    private RentalDTO mapToDTO(Rental rental){
        return new RentalDTO(rental.getISBN(), rental.getUserId(), rental.getRentalId(), rental.getDueDate());
    }

    private Rental mapToDomain(RentalDTO rentalDTO){
        return new Rental(rentalDTO.getISBN(), rentalDTO.getUserId(), rentalDTO.getRentalId(), rentalDTO.getDueDate());
    }

   private List<RentalDTO> mapToDTO(List<Rental> rentalList){
        return rentalList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private List<Rental> mapToDomain(List<RentalDTO> rentalDTOList){
        return rentalDTOList.stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }
}