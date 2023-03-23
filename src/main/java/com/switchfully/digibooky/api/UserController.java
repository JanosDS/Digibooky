package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.user.Feature;
import com.switchfully.digibooky.dto.user.CreateUserDTO;
import com.switchfully.digibooky.dto.user.UserDTO;
import com.switchfully.digibooky.service.SecurityService;
import com.switchfully.digibooky.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	private final SecurityService securityService;

	public UserController(UserService userService, SecurityService securityService) {
		this.securityService = securityService;
		this.userService = userService;
	}

	@PostMapping(path = "/addMember", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO createNewMemberUser(@RequestBody CreateUserDTO createUserDTO){
		return userService.createNewMemberUser(createUserDTO);
	}
	@PostMapping(path = "/addLibrarian", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO createNewLibrarianUser(@RequestHeader String authorization, @RequestBody CreateUserDTO createUserDTO){
		securityService.validateAuthorization(authorization, Feature.CREATE_NEW_LIBRARIAN);
		return userService.createNewLibrarianUser(createUserDTO);
	}
	@PostMapping(path = "/addAdmin", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO createNewAdminUser(@RequestHeader String authorization, @RequestBody CreateUserDTO createUserDTO){
		securityService.validateAuthorization(authorization, Feature.CREATE_NEW_ADMIN);
		return userService.createNewAdminUser(createUserDTO);
	}
	@GetMapping
	public UserDTO getUserByInss(@RequestParam(name= "inss") String inss){
		return userService.getUserByInss(inss);
	}

	@GetMapping(path = "/members")
	public List<UserDTO> getAllMembers(@RequestHeader String authorization){
		securityService.validateAuthorization(authorization, Feature.VIEW_ALL_MEMBERS);
		return userService.getAllMembers();
	}



}
