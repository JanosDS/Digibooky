package com.switchfully.digibooky.dto.user;

import com.switchfully.digibooky.domain.Address;
import com.switchfully.digibooky.dto.address.CreateAddressDTO;

import javax.management.relation.Role;
import java.util.UUID;

public class CreateUserDTO {
	private String lastName;
	private String email;
	private CreateAddressDTO address;
	private String iNSS;

	public CreateUserDTO(String lastName, String email, CreateAddressDTO newAddress, String iNSS) {
		this.lastName = lastName;
		this.email = email;
		this.address = newAddress;
		this.iNSS = iNSS;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public CreateAddressDTO getAddress() {
		return address;
	}

	public String getiNSS() {
		return iNSS;
	}
}
