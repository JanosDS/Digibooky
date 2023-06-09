package com.switchfully.digibooky.dto.author;

import com.switchfully.digibooky.domain.Author;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorMapper {

	public Author mapToDomain(AuthorDTO authorDTO) {
		return new Author(authorDTO.getFirstName(), authorDTO.getLastName());
	}

	public List<Author> mapToDomain(List<AuthorDTO> authorDTOList) {
		return authorDTOList.stream()
				.map(this::mapToDomain)
				.toList();
	}

	public AuthorDTO mapToDto(Author author) {
		return new AuthorDTO(author.getFirstName(), author.getLastName());
	}

	public List<AuthorDTO> mapToDTO(List<Author> authorList) {
		return authorList.stream()
				.map(this::mapToDto)
				.toList();
	}
}
