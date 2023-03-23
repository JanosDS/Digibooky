package com.switchfully.digibooky.service;


import com.switchfully.digibooky.domain.user.Feature;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.domain.user.UuidPassword;
import com.switchfully.digibooky.exception.UnauthorizedException;
import com.switchfully.digibooky.exception.UserNotFoundException;
import com.switchfully.digibooky.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

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
		UuidPassword uuidPassword;
		try {
			uuidPassword = getUuidPassword(auth);
		} catch(IllegalArgumentException exception){
			throw new UnauthorizedException("No valid UUID was provided.");
		}
		User user = userRepository.getUserByUuid(uuidPassword.getUuid())
				.orElseThrow(() -> new UserNotFoundException("No user found with UUID: " + uuidPassword.getUuid()));

		if (!user.doesPasswordMatch(uuidPassword.getPassword())) {
			throw new UnauthorizedException("Wrong password for user used.");
		}
		if (!user.hasAccessTo(feature)) {
			throw new UnauthorizedException("User has no access to this feature.");
		}

	}

	private UuidPassword getUuidPassword(String auth) {
		String decodedUsernamePassword = new String(Base64.getDecoder().decode(auth.substring("basic ".length())));
		UUID uuid = UUID.fromString(decodedUsernamePassword.substring(0, decodedUsernamePassword.indexOf(":")));
		String password = decodedUsernamePassword.substring(decodedUsernamePassword.indexOf(":") + 1);
		return new UuidPassword(uuid, password);
	}
}
