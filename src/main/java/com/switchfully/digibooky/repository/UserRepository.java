package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.domain.user.Role;
import com.switchfully.digibooky.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private List<User> userList;
    public UserRepository() {
        this.userList = new ArrayList<>();

        //Add user for testing purposes
        userList.add(new User("Janos", "Descheemaeker", "janosdescheemaeker@hotmail.com", new Address("Leiestraat", "29", "9000", "Gent", "Belgium"), "MyINSS", Role.MEMBER, "123"));
		//Add an Admin for testing purposes
		userList.add(new User("Admin", "Admin", "admin@admin.be", new Address("Adminstreet", "1", "9001", "AdminCity", "Belgium"), "AdminINSS", Role.ADMIN, "admin"));
	}

    public List<User> getUserList() {
        return userList;
    }

	public User addUser(User user) {
		userList.add(user);
		return user;
	}

	public Optional<User> getUserByINSS(String INSS) {
		return userList.stream()
				.filter(user -> user.getInss().equals(INSS))
				.findFirst();
	}

	public Optional<User> findUserForUsername(String username) {
		return userList.stream()
				.filter(user -> user.getFirstName().equals(username))
				.findFirst();
	}

	public List<User> getAllMembers() {
		return userList.stream()
				.filter(user -> user.getRole().equals(Role.MEMBER))
				.toList();
	}

	public User getUserByName(String firstName, String lastName) {
		return userList.stream()
				.filter(user -> user.getUserId().equals(lastName))
				.filter(user -> user.getUserId().equals(firstName))
				.findFirst()
				.orElse(null);
	}
}
