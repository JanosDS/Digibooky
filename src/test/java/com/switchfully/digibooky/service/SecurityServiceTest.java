package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.domain.user.Feature;
import com.switchfully.digibooky.domain.user.Role;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.exception.UnauthorizedException;
import com.switchfully.digibooky.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SecurityServiceTest {

	private SecurityService securityService;
	private UserRepository userRepository;

	@BeforeEach
	void setup() {
		this.userRepository = new UserRepository();
		this.securityService = new SecurityService(userRepository);
	}

	public String encodeAuth(String stringToEncode) {
		return "Basic " + Base64.getEncoder().encodeToString(stringToEncode.getBytes());
	}

	@Nested
	class AdminSecurityTests {

		private User admin;
		private User notAdmin;

		@BeforeEach
		void setup() {
			this.admin = new User("Admin", "Admin", "admin@admin.be", new Address("Adminstreet", "1", "9001", "AdminCity", "Belgium"), "AdminINSS", Role.ADMIN, "admin");
			this.notAdmin = new User("notAdmin", "Admin", "notAdmin@admin.be", new Address("Adminstreet", "1", "9001", "AdminCity", "Belgium"), "notAdminINSS", Role.MEMBER, "admin");

			userRepository.addUser(admin);
			userRepository.addUser(notAdmin);
		}


		@Test
		@DisplayName("security validation test")
		void validAdminProvided_featureGetAllMembers() {
			//create Authtoken
			String usernamePassword = admin.getUserId() + ":" + admin.getPassword();
			securityService.validateAuthorization(encodeAuth(usernamePassword), Feature.VIEW_ALL_MEMBERS);
		}

		@Test
		@DisplayName("security validation test")
		void invalidAdminProvided_featureGetAllMembers_throwException() {
			//create Authtoken
			String usernamePassword = notAdmin.getUserId() + ":" + notAdmin.getPassword();
			Exception exception = assertThrows(UnauthorizedException.class, () -> {
				securityService.validateAuthorization(encodeAuth(usernamePassword), Feature.VIEW_ALL_MEMBERS);
			});

		}
	}

}