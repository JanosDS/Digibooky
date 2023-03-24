package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.user.address.AddressMapper;
import com.switchfully.digibooky.dto.user.address.CreateAddressDTO;
import com.switchfully.digibooky.dto.user.CreateUserDTO;
import com.switchfully.digibooky.dto.user.UserDTO;
import com.switchfully.digibooky.dto.user.UserMapper;
import com.switchfully.digibooky.exception.MandatoryFieldException;
import com.switchfully.digibooky.repository.UserRepository;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

	private UserService userService;

	@BeforeEach
	void setup(){
		this.userService = new UserService(new UserRepository(), new UserMapper(new AddressMapper()));
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
		@DisplayName("Validate INSS when it is unique")
		void uniqueINSS_isValid(){
			String INSS = "whatever";
			assertTrue(userService.validateUniqueInss(INSS));
		}

		@Test
		@DisplayName("Validate INSS when it is not unique")
		void NotUniqueINSS_isInvalid(){
			String INSS = "MyINSS";
			assertFalse(userService.validateUniqueInss(INSS));
		}
	}

	@Nested
	@DisplayName("Validate mandatory member fields")
	class mandatoryMemberFieldsValidation{
		@Test
		@DisplayName("Validate when every mandatory field is filled in")
		void allMandatoryFieldsAreFilledIn_isValid(){
			CreateUserDTO newUser = new CreateUserDTO("Firstname", "De", "j", new CreateAddressDTO("street","25", "PC", "City", "Country"), "MyINSS");
			userService.validateMandatoryMemberFields(newUser);
		}

		@Test
		@DisplayName("Validate when lastname field is not filled in")
		void lastnameFieldNotFilledIn_isInvalid(){
			CreateUserDTO newUser = new CreateUserDTO("Firstname", null, "j", new CreateAddressDTO("street","25", "PC", "City", "Country"), "MyINSS");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryMemberFields(newUser);
			});
		}

		@Test
		@DisplayName("Validate when email field is not filled in")
		void emailFieldNotFilledIn_isInvalid(){
			CreateUserDTO newUser = new CreateUserDTO("Firstname", "De", null, new CreateAddressDTO("street","25", "PC", "City", "Country"), "MyINSS");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryMemberFields(newUser);
			});
		}

		@Test
		@DisplayName("Validate when city field is not filled in")
		void cityFieldNotFilledIn_isInvalid(){
			CreateUserDTO newUser = new CreateUserDTO("Firstname", "De", "j", new CreateAddressDTO("street","25", "PC", null, "Country"), "MyINSS");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryMemberFields(newUser);
			});
		}

		@Test
		@DisplayName("Validate when INSS field is not filled in")
		void INSSFieldNotFilledIn_isInvalid(){
			CreateUserDTO newUser = new CreateUserDTO("Firstname", "De", "j", new CreateAddressDTO("street","25", "PC", "City", "Country"), null);
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryMemberFields(newUser);
			});
		}
	}

	@Nested
	@DisplayName("Validate mandatory admin & librarian fields")
	class mandatoryAdminLibrarianFieldsValidation{
		@Test
		@DisplayName("Validate when every mandatory field is filled in")
		void allMandatoryFieldsAreFilledIn_isValid(){
			CreateUserDTO newAdmin = new CreateUserDTO("Firstname", "De", "admin@admin.be", new CreateAddressDTO("street","25", "PC", "City", "Country"), "MyAdminINSS");
			userService.validateMandatoryAdminAndLibrarianFields(newAdmin);
		}

		@Test
		@DisplayName("Validate when lastname field is not filled in")
		void lastnameFieldNotFilledIn_isInvalid(){
			CreateUserDTO newUser = new CreateUserDTO("Firstname", null, "j", new CreateAddressDTO("street","25", "PC", "City", "Country"), "MyINSS");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryAdminAndLibrarianFields(newUser);
			});
		}
		@Test
		@DisplayName("Validate when firstname field is not filled in")
		void firstnameFieldNotFilledIn_isInvalid(){
			CreateUserDTO newUser = new CreateUserDTO(null, "lastname", "j", new CreateAddressDTO("street","25", "PC", "City", "Country"), "MyINSS");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryAdminAndLibrarianFields(newUser);
			});
		}
		@Test
		@DisplayName("Validate when email field is not filled in")
		void emailFieldNotFilledIn_isInvalid(){
			CreateUserDTO newUser = new CreateUserDTO("Admin", "lastname", null, new CreateAddressDTO("street","25", "PC", "City", "Country"), "MyINSS");
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				userService.validateMandatoryAdminAndLibrarianFields(newUser);
			});
		}
	}

	@Test
	@DisplayName("Test creation of a new member user, and finding it in the repo")
	void addNewMemberUser(){
		CreateUserDTO newUser = new CreateUserDTO("Firstname", "De", "mail@mail.com", new CreateAddressDTO("street","25", "PC", "City", "Country"), "xxx");
		UserDTO resultUserDTO = userService.createNewMemberUser(newUser);
		UserDTO expectedUserDTO = userService.getUserByInss(newUser.getInss());
		Assertions.assertEquals(expectedUserDTO, resultUserDTO);
	}

	@Test
	@DisplayName("Test creation of a new librarian user, and finding it in the repo")
	void addNewLibrarianUser(){
		CreateUserDTO newUser = new CreateUserDTO("Firstname", "De", "mail@mail.com", new CreateAddressDTO("street","25", "PC", "City", "Country"), "xxx");
		UserDTO resultUserDTO = userService.createNewLibrarianUser(newUser);
		UserDTO expectedUserDTO = userService.getUserByInss(newUser.getInss());
		Assertions.assertEquals(expectedUserDTO, resultUserDTO);
	}

	@Test
	@DisplayName("Test creation of a new admin user, and finding it in the repo")
	void addNewAdminUser(){
		CreateUserDTO newUser = new CreateUserDTO("Firstname", "De", "mail@mail.com", new CreateAddressDTO("street","25", "PC", "City", "Country"), "xxx");
		UserDTO resultUserDTO = userService.createNewAdminUser(newUser);
		UserDTO expectedUserDTO = userService.getUserByInss(newUser.getInss());
		Assertions.assertEquals(expectedUserDTO, resultUserDTO);
	}

}