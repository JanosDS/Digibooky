package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressRepository {
    private static List<Address> addressList;
}