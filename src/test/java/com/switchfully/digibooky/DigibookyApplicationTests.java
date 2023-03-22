package com.switchfully.digibooky;

import com.google.gson.Gson;
import com.switchfully.digibooky.repository.BookRepository;
import io.restassured.http.ContentType;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;

import com.switchfully.digibooky.domain.author.Author;
import com.switchfully.digibooky.domain.book.Book;
import com.switchfully.digibooky.dto.book.BookDTO;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DigibookyApplicationTests {

    @Value("8080")
    private int port;

    @Test
    void givenBookList_whenGetAllBooks_thenReturnListInJSON() throws JSONException {

    }

}