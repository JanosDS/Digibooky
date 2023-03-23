package com.switchfully.digibooky;

import org.json.JSONException;

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