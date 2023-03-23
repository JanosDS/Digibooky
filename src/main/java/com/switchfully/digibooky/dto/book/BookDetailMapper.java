package com.switchfully.digibooky.dto.book;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.dto.author.AuthorMapper;
import org.springframework.stereotype.Component;

@Component
public class BookDetailMapper {
    AuthorMapper authorMapper;

    public BookDetailMapper(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    public BookDetailDTO mapToDTO(Book book){
        return new BookDetailDTO(book.getIsbn(), book.getTitle(), authorMapper.mapToDTO(book.getAuthorList()), book.getSummary(), book.isAvailable());
    }

}
