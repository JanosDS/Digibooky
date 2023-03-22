package com.switchfully.digibooky.api;

import com.switchfully.digibooky.dto.user.CreateUserDTO;
import com.switchfully.digibooky.dto.user.UserDTO;
import com.switchfully.digibooky.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO createNewMemberUser(@RequestBody CreateUserDTO createUserDTO){
		return userService.createNewMemberUser(createUserDTO);
	}

	@GetMapping
	public UserDTO getUserByINSS(@RequestParam(name="INSS") String INSS){
		return userService.getUserByINSS(INSS);
	}



}
