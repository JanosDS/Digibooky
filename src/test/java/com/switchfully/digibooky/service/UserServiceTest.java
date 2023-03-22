package com.switchfully.digibooky.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

	@Nested
	@DisplayName("Mail validation")
	class mailValidation{
		@Test
		@DisplayName("Validate a correct email address")
		void correctMailAddress_isValidate(){
			UserService userService = new UserService();
			String emailaddress = "janosdescheemaeker@hotmail.com";
			Assertions.assertEquals(true, userService.validateEmail(emailaddress));
		}
		@Test
		@DisplayName("Validate a correct complicated email address")
		void correctComplicatedMailAddress_isValidate(){
			UserService userService = new UserService();
			String emailaddress = "janos.descheemaeker@minfin.fed.be";
			Assertions.assertEquals(true, userService.validateEmail(emailaddress));
		}
		@Test
		@DisplayName("Validate an incorrect email address")
		void incorrectMailAddress_isNotValidate(){
			UserService userService = new UserService();
			String emailaddress = "janosdescheemaeker@hotmail";
			Assertions.assertEquals(false, userService.validateEmail(emailaddress));
		}
	}

}