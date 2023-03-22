package com.switchfully.digibooky.dto.author;

import com.switchfully.digibooky.domain.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {


    public Author mapToDomain() {
        /** implement this **/
        return new Author("firstName", "lastName");
    }
    
    public AuthorDTO mapToDto(Author author) {
        return new AuthorDTO()
                .setFirstName(author.getFirstName())
                .setLastName(author.getLastName());
    }
}
