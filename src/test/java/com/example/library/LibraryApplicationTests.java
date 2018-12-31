package com.example.library;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.net.URI;
import java.util.Arrays;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.library.presentation.controller.payload.BookInfoRequest;
import com.example.library.presentation.controller.payload.BookInfoResponse;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.generator.ValueGenerators;
import com.ninja_squad.dbsetup.operation.Operation;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LibraryApplicationTests {
  
  private static final Operation RESET_TABLES = Operations.deleteAllFrom("borrowers", "books");
  private static final Operation INSERT_BOOKS =
          Operations.insertInto("books")
                    .withGeneratedValue("id", ValueGenerators.sequence().startingAt(1L)) // これをつけないと、テストケース毎にシーケンスがリセットされない
                    .columns("title", "price", "stock")
                    .values("Sample Book X", 1500, 5)
                    .values("Sample Book Y", 2000, 10)
                    .values("Sample Book Z", 10000, 1)
                    .build();
  private static final Operation INSERT_BORROWERS =
          Operations.insertInto("borrowers")
                    .columns("name", "book_id")
                    .values("taro", 1)
                    .values("taro", 2)
                    .values("jiro", 2)
                    .build();
  
  @LocalServerPort
  private int port;
  
  @Autowired
  private TestRestTemplate template;
  
  @Autowired
  DataSource dataSource;
  
  private void dbSetUp(Operation operation) {
    Destination destination = new DataSourceDestination(dataSource);
    DbSetup dbSetup = new DbSetup(destination, operation);
    dbSetup.launch();
  }
  
  @Test
  public void test_指定したIDの書籍情報を取得できる() throws Exception {
    dbSetUp(Operations.sequenceOf(RESET_TABLES, INSERT_BOOKS, INSERT_BORROWERS));
    
    BookInfoResponse expected = new BookInfoResponse(
        2,
        "Sample Book Y",
        2000,
        10,
        Arrays.asList("taro", "jiro"));

    URI uri = new URI("http://localhost:" + port + "/library/api/v1/books/2");
    
    BookInfoResponse actual = template.getForObject(uri, BookInfoResponse.class);
    assertThat(actual, is(expected));
  }
  
  @Test
  public void test_全書籍情報を取得できる() throws Exception {
    fail("not implemented.");
  }
  
  @Test
  public void test_書籍を登録できる() throws Exception {
    dbSetUp(Operations.sequenceOf(RESET_TABLES, INSERT_BOOKS, INSERT_BORROWERS));
    
    BookInfoResponse expected = new BookInfoResponse(
        4,
        "Sample Book W",
        15000,
        3,
        Arrays.asList());
    
    URI uri = new URI("http://localhost:" + port + "/library/api/v1/books");
    
    BookInfoRequest request = new BookInfoRequest("Sample Book W", 15000, 3);
    
    BookInfoResponse actual = template.postForObject(uri, request, BookInfoResponse.class);
    assertThat(actual, is(expected));
  }

}
