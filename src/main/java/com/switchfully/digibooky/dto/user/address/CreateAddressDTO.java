package com.switchfully.digibooky.dto.user.address;

public class CreateAddressDTO {

	private final String street;
	private final String houseNumber;
	private final String postalCode;
	private final String city;
	private final String country;

	public CreateAddressDTO(String street, String houseNumber, String postalCode, String city, String country) {
		this.street = street;
		this.houseNumber = houseNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCountry() {
		return country;
	}
}
