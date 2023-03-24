package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.user.Feature;
import com.switchfully.digibooky.dto.user.CreateUserDTO;
import com.switchfully.digibooky.dto.user.UserDTO;
import com.switchfully.digibooky.service.SecurityService;
import com.switchfully.digibooky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	private final SecurityService securityService;

	@Autowired
	public UserController(UserService userService, SecurityService securityService) {
		this.securityService = securityService;
		this.userService = userService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/admin", consumes = "application/json", produces = "application/json")
	public UserDTO createNewAdminUser(@RequestHeader String authorization, @RequestBody CreateUserDTO createUserDTO) {
		securityService.validateAuthorization(authorization, Feature.CREATE_NEW_ADMIN);
		return userService.createNewAdminUser(createUserDTO);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/member", consumes = "application/json", produces = "application/json")
	public UserDTO createNewMemberUser(@RequestBody CreateUserDTO createUserDTO) {
		return userService.createNewMemberUser(createUserDTO);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/librarian", consumes = "application/json", produces = "application/json")
	public UserDTO createNewLibrarianUser(@RequestHeader String authorization, @RequestBody CreateUserDTO createUserDTO) {
		securityService.validateAuthorization(authorization, Feature.CREATE_NEW_LIBRARIAN);
		return userService.createNewLibrarianUser(createUserDTO);
	}

    @ResponseStatus(HttpStatus.OK)
	@GetMapping(path ="/{inss}")
	public UserDTO getUserByInss(@PathVariable String inss) {
		return userService.getUserByInss(inss);
	}

    @ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/member")
	public List<UserDTO> getAllMembers(@RequestHeader String authorization) {
		securityService.validateAuthorization(authorization, Feature.VIEW_ALL_MEMBERS);
		return userService.getAllMembers();
	}

}
