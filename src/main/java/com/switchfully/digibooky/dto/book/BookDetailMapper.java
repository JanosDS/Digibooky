package com.switchfully.digibooky.dto.book;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Rental;
import com.switchfully.digibooky.dto.author.AuthorMapper;

public class BookDetailMapper {
    AuthorMapper authorMapper;

    public BookDetailMapper(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    public BookDetailDTO mapToDTO(Book book, Rental rental){
        return new BookDetailDTO(book.getISBN(), book.getTitle(), authorMapper.mapToDTO(book.getAuthorList()), book.getSummary(), book.isAvailable());
    }

}
