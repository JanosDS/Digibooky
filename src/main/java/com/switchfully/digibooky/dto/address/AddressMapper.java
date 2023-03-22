package com.switchfully.digibooky.dto.address;

import com.switchfully.digibooky.domain.Address;
import com.switchfully.digibooky.dto.address.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressDTO mapToDTO(Address address) {
        return new AddressDTO(address.getStreet(), address.getNumber(), address.getPostalCode(), address.getCity(), address.getCountry());

    }

    public Address mapToDomain(AddressDTO addressDTO) {
        return new Address(addressDTO.getStreet(), addressDTO.getHouseNumber(), addressDTO.getPostalCode(), addressDTO.getCity(), addressDTO.getCountry());
    }

    public Address mapToDomain(CreateAddressDTO addressDTO) {
        return new Address(addressDTO.getStreet(), addressDTO.getHouseNumber(), addressDTO.getPostalCode(), addressDTO.getCity(), addressDTO.getCountry());
    }
}
