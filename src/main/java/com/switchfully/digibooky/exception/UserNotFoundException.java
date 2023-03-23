package com.switchfully.digibooky.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.webjars.NotFoundException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserNotFoundException extends NotFoundException {
	public UserNotFoundException(String message) {
		super(message);
	}
}
