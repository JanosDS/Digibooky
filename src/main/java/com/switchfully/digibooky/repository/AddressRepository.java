package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Address;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressRepository {
    private static List<Address> addressList;
}