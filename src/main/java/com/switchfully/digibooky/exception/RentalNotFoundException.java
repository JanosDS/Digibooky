package com.switchfully.digibooky.exception;

import org.webjars.NotFoundException;

public class RentalNotFoundException extends NotFoundException {
	public RentalNotFoundException(String message) {
		super(message);
	}
}
