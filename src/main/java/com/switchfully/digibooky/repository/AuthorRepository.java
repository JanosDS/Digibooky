package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {
    private List<Author> authorList;

    public AuthorRepository() {
        this.authorList = new ArrayList<>();
    }

    public Author addAuthor(Author author){
        authorList.add(author);
        return author;
    }
}
