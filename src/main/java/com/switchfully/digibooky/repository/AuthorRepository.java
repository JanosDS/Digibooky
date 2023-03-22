package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {
    private final List<Author> authorList;

    public AuthorRepository() {
        this.authorList = new ArrayList<>();
    }
}
