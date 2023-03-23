package com.switchfully.digibooky.dto.author;

import com.switchfully.digibooky.domain.Author;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {


    public Author mapToDomain(AuthorDTO authorDTO) {
        return new Author(authorDTO.getFirstName(), authorDTO.getLastName());
    }

    public List<Author> mapToDomain(List<AuthorDTO> authorDTOList){
        return authorDTOList.stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }
    
    public AuthorDTO mapToDto(Author author) {
        return new AuthorDTO()
                .setFirstName(author.getFirstName())
                .setLastName(author.getLastName());
    }

    public List<AuthorDTO> mapToDTO(List<Author> authorList){
        return authorList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
