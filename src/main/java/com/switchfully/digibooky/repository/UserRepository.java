package com.switchfully.digibooky.repository.user;

import com.switchfully.digibooky.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.UUID;

@Repository
public class UserRepository {
    private HashMap<UUID, User> userById;
    public UserRepository() {
        this.userById = new HashMap<>();
    }

}
