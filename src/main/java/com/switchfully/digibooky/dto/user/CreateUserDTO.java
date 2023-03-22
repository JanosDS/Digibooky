package com.switchfully.digibooky.dto.user;

import com.switchfully.digibooky.dto.user.address.CreateAddressDTO;

public class CreateUserDTO {
	private String firstName;
	private String lastName;
	private String email;
	private CreateAddressDTO address;
	private String INSS;

	public CreateUserDTO(String firstName, String lastName, String email, CreateAddressDTO address, String INSS) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.INSS = INSS;
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

	public String getINSS() {
		return INSS;
	}

	public String getFirstName() {
		return firstName;
	}
}
