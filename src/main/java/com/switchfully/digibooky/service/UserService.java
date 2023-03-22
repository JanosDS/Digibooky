package com.switchfully.digibooky.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {



	public boolean validateEmail(String email){
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}


}
