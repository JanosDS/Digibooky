package com.switchfully.digibooky.exception;

public class InvalidEmailException extends IllegalArgumentException{
	public InvalidEmailException(String s) {
		super(s);
	}
}
