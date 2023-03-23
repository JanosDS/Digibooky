package com.switchfully.digibooky.service;


import com.switchfully.digibooky.domain.user.Feature;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.dto.user.UsernamePassword;
import com.switchfully.digibooky.exception.UnauthorizedException;
import com.switchfully.digibooky.exception.UserNotFoundException;
import com.switchfully.digibooky.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {

	private final UserRepository userRepository;

	public SecurityService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void validateAuthorization(String auth, Feature feature){
		if(auth == null){
			throw new UnauthorizedException("Not authorized.");
		}
		UsernamePassword usernamePassword = getUsernamePassword(auth);

		User user = userRepository.findUserForUsername(usernamePassword.getUsername())
				.orElseThrow(() -> new UserNotFoundException("No user found with username: " + usernamePassword.getUsername()));

		if (!user.doesPasswordMatch(usernamePassword.getPassword())) {
			throw new UnauthorizedException("Wrong password for user used.");
		}
		if (!user.hasAccessTo(feature)) {
			throw new UnauthorizedException("User has no access to this feature.");
		}

	}

	private UsernamePassword getUsernamePassword(String auth) {
		String decodedUsernamePassword = new String(Base64.getDecoder().decode(auth.substring("basic ".length())));
		String username = decodedUsernamePassword.substring(0, decodedUsernamePassword.indexOf(":"));
		String password = decodedUsernamePassword.substring(decodedUsernamePassword.indexOf(":") + 1);
		return new UsernamePassword(username, password);
	}
}
