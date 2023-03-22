package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.user.CreateUserDTO;
import com.switchfully.digibooky.dto.user.UserDTO;
import com.switchfully.digibooky.dto.user.UserMapper;
import com.switchfully.digibooky.exception.InvalidEmailException;
import com.switchfully.digibooky.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}




	public void validateEmail(String email){
		if(!isValidEmailFormat(email)){
			throw new InvalidEmailException( email + " is not a valid email address.");
		}
		if(!isUniqueEmail(email)){
			throw new InvalidEmailException( email + " is not a unique email address.");
		}
	}


	public boolean isValidEmailFormat(String email){
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

	public boolean isUniqueEmail(String email){
		return userRepository.getUserList()
				.stream()
				.noneMatch(user -> user.getEmail().equals(email));
	}

	public boolean validateUniqueINSS(String INSS){

		return false;
	}




}
