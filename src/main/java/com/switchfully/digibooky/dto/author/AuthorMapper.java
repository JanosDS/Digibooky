package com.switchfully.digibooky.dto.author;

import com.switchfully.digibooky.domain.author.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {


    public Author mapToDomain() {
        /** implement this **/
        return new Author("firstName", "lastName");
    }
    
    public AuthorDto mapToDto(Author author) {
        return new AuthorDto()
                .setFirstName(author.getFirstName())
                .setLastName(author.getLastName());
    }
}
