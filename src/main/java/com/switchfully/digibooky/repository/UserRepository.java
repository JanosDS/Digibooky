package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.domain.user.Role;
import com.switchfully.digibooky.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
	private List<User> userList;

	public UserRepository() {
		this.userList = new ArrayList<>();

		//Add user for testing purposes
		userList.add(new User("Janos", "Descheemaeker", "janosdescheemaeker@hotmail.com", new Address("Leiestraat", "29", "9000", "Gent", "Belgium"), "MyINSS", Role.MEMBER, null));
		//Add user for testing purposes
		userList.add(new User("Admin", "admin", "admin@admin.com", new Address("Leiestraat", "29", "9000", "Gent", "Belgium"), "AdminINSS", Role.ADMIN, "admin"));
	}

	public List<User> getUserList() {
		return userList;
	}

	public User addUser(User user) {
		userList.add(user);
		return user;
	}

	public Optional<User> getUserByInss(String inss) {
		return userList.stream()
				.filter(user -> user.getInss().equals(inss))
				.findFirst();
	}

	public Optional<User> getUserByUuid(UUID userId) {
		return userList.stream()
				.filter(user -> user.getUserId().equals(userId))
				.findFirst();
	}

	public List<User> getAllMembers() {
		return userList.stream()
				.filter(user -> user.getRole().equals(Role.MEMBER))
				.collect(Collectors.toList());
	}
}
