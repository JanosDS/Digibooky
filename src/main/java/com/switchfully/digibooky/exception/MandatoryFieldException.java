package com.switchfully.digibooky.exception;

public class MandatoryFieldException extends NullPointerException{
	public MandatoryFieldException(String s) {
		super(s);
	}
}
