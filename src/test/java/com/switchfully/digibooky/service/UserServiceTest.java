package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.dto.user.UserMapper;
import com.switchfully.digibooky.repository.UserRepository;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
	private UserService userService;

	@BeforeEach
	void setup(){
		this.userService = new UserService(new UserRepository(), new UserMapper());
	}

	@Nested
	@DisplayName("Mail validation")
	class mailValidation{

		@Test
		@DisplayName("Validate a correct email address")
		void correctMailAddress_isValidate(){
			String emailAddress = "janosdescheemaeker@hotmail.com";
			assertTrue(userService.isValidEmailFormat(emailAddress));
		}
		@Test
		@DisplayName("Validate a correct complicated email address")
		void correctComplicatedMailAddress_isValidate(){
			String emailAddress = "janos.descheemaeker@minfin.fed.be";
			assertTrue(userService.isValidEmailFormat(emailAddress));
		}
		@Test
		@DisplayName("Validate an incorrect email address")
		void incorrectMailAddress_isNotValidate(){
			String emailAddress = "janosdescheemaeker@hotmail";
			assertFalse(userService.isValidEmailFormat(emailAddress));
		}

		@Test
		@DisplayName("Validate email address when it is unique")
		void uniqueMailAddress_isValid(){
			String emailAddress = "janosdescheemaeker2@hotmail.com";
			assertTrue(userService.isUniqueEmail(emailAddress));
		}

		@Test
		@DisplayName("Validate email address when it is not unique")
		void NotUniqueMailAddress_isNotValid(){
			String emailAddress = "janosdescheemaeker@hotmail.com";
			assertFalse(userService.isUniqueEmail(emailAddress));
		}

	}

	@Nested
	@DisplayName("INSS validation")
	class inssValidation{
		@Test
		@DisplayName("unique INSS validation")
		void uniqueINSS_isValid(){
			String INSS = "whatever";


		}
	}


}