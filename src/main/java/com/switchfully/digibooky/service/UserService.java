package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.user.Role;
import com.switchfully.digibooky.dto.user.CreateUserDTO;
import com.switchfully.digibooky.dto.user.UserDTO;
import com.switchfully.digibooky.dto.user.UserMapper;
import com.switchfully.digibooky.exception.InvalidEmailException;
import com.switchfully.digibooky.exception.InvalidINSSException;
import com.switchfully.digibooky.exception.MandatoryFieldException;
import com.switchfully.digibooky.repository.UserRepository;
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

	public UserDTO createNewUser(CreateUserDTO newUser){
		validateMandatoryFields(newUser);
		validateINSS(newUser.getINSS());
		validateEmail(newUser.getEmail());
		return userMapper.mapToDTO(userRepository.addUser(userMapper.mapToDomain(newUser)));
	}

	public UserDTO createNewMemberUser(CreateUserDTO newUser){
		return createNewUser(Role.setRoleToMember(newUser));
	}

	public UserDTO getUserByINSS(String INSS){
		return userRepository.getUserByINSS(INSS)
				.map(userMapper::mapToDTO)
				.orElse(null);
	}

	public void validateMandatoryFields(CreateUserDTO newUser){
		if(newUser.getLastName() == null){
			throw new MandatoryFieldException("The lastname field cannot be empty");
		}
		if(newUser.getINSS() == null){
			throw new MandatoryFieldException("The INSS field cannot be empty");
		}
		if(newUser.getEmail() == null){
			throw new MandatoryFieldException("The email field cannot be empty");
		}
		if(newUser.getAddress().getCity() == null){
			throw new MandatoryFieldException("The address city field cannot be empty");
		}
	}

	public void validateINSS(String INSS){
		if(!validateUniqueINSS(INSS)){
			throw new InvalidINSSException( INSS + " is not a unique INSS.");
		}
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
		return userRepository.getUserList()
				.stream()
				.noneMatch(user -> user.getINSS().equals(INSS));
	}




}
