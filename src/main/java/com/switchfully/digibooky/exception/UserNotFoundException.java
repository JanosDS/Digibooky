package com.switchfully.digibooky.exception;

import org.webjars.NotFoundException;

public class UserNotFoundException extends NotFoundException {
	public UserNotFoundException(String message) {
		super(message);
	}
}
