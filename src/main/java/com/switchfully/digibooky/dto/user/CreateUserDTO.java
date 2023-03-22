package com.switchfully.digibooky.dto.user;

import com.switchfully.digibooky.dto.user.address.CreateAddressDTO;
import com.switchfully.digibooky.domain.user.Role;

public class CreateUserDTO {
	private String firstName;
	private String lastName;
	private String email;
	private CreateAddressDTO address;
	private String inss;
	private Role role;

	public CreateUserDTO(String firstName, String lastName, String email, CreateAddressDTO address, String inss) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.inss = inss;
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

	public String getInss() {
		return inss;
	}

	public String getFirstName() {
		return firstName;
	}

	public Role getRole() {
		return role;
	}

	public CreateUserDTO setRole(Role role) {
		this.role = role;
		return this;
	}
}
