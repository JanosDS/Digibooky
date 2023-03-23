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
        userList.add(new User("Janos", "Descheemaeker", "janosdescheemaeker@hotmail.com", new Address("Leiestraat", "29", "9000", "Gent", "Belgium"), "MyINSS", Role.MEMBER));
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

	public Optional<User> getUserByFirstname(String firstname) {
		return userList.stream()
				.filter(user -> user.getFirstName().equals(firstname))
				.findFirst();
	}

	public Optional<User> getUserByUuid(UUID uuid){
		return userList.stream()
				.filter(user -> user.getUserId().equals(uuid))
				.findFirst();
	}

	public List<User> getAllMembers(){
		return userList.stream()
				.filter(user -> user.getRole().equals(Role.MEMBER))
				.collect(Collectors.toList());
	}

	public Optional<User> getUserByName(String lastName, String firstName) {
		return userList.stream()
				.filter(user -> user.getFirstName().equals(firstName) && user.getLastName().equals(lastName))
				.findFirst();
	}
}
