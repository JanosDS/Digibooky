package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.address.AddressDTO;
import com.switchfully.digibooky.dto.address.AddressMapper;
import com.switchfully.digibooky.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    private AddressMapper addressMapper;

    private AddressDTO addressDTO;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper, AddressDTO addressDTO) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.addressDTO = addressDTO;
    }
}
