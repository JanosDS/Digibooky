package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.book.Book;
import com.switchfully.digibooky.dto.book.BookDTO;
import com.switchfully.digibooky.dto.book.BookMapper;
import com.switchfully.digibooky.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public BookDTO getBookById(String id) {
        return bookMapper.mapToDTO(bookRepository.getById(id));
    }

    public List<BookDTO> getBooksByISBN(String ISBN) {
        boolean wildcard = ISBN.contains("*");
        if (!wildcard) {
            return List.of(getBookById(ISBN));
        } else {
            String ISBNWithoutWildcard = ISBN.replace("*", "");
            List<Book> bookList = bookRepository.getAllBooks();
            List<Book> bookListWithWildcard = bookList.stream()
                    .filter(book -> book.getISBN().contains(ISBNWithoutWildcard))
                    .collect(Collectors.toList());
            return bookMapper.mapToDTO(bookListWithWildcard);
        }
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book bookToStore = bookMapper.mapToDomain(bookDTO);
        bookRepository.putBookInList(bookToStore);
        return bookDTO;
    }
}
