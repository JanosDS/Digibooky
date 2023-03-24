package com.switchfully.digibooky.exception;

import org.webjars.NotFoundException;

public class BookNotFoundException extends NotFoundException {
	public BookNotFoundException(String message) {
		super(message);
	}
}
