package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.domain.user.Role;
import com.switchfully.digibooky.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.*;

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

	public User getUserByName(String firstName, String lastName) {
		return userList.stream()
				.filter(user -> user.getUserId().equals(lastName))
				.filter(user -> user.getUserId().equals(firstName))
				.findFirst()
				.orElse(null);
	}
}
