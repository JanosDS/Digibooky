package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Address;
import com.switchfully.digibooky.domain.user.Role;
import com.switchfully.digibooky.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
}
