package com.switchfully.digibooky.dto.author;

import com.switchfully.digibooky.domain.author.Author;
import com.switchfully.digibooky.domain.book.Book;
import com.switchfully.digibooky.dto.book.BookDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<AuthorDto> mapToDTO(List<Author> authorList){
        return authorList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
