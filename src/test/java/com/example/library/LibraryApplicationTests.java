package com.example.library;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.net.URI;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.library.presentation.controller.payload.BookInfoResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LibraryApplicationTests {
  
  @LocalServerPort
  private int port;
  
  @Autowired
  private TestRestTemplate template;
  
  @Before
  public void setUp() {
  }

  @Test
  public void contextLoads() {
  }
  
  @Test
  public void test_指定したIDの書籍情報を取得できる() throws Exception {
    BookInfoResponse expected = new BookInfoResponse(
        5,
        "Sample Book A",
        1200,
        3,
        Arrays.asList("taro", "jiro"));

    URI uri = new URI("http://localhost:" + port + "/library/api/v1/book/1");

    
    BookInfoResponse actual = template.getForObject(uri, BookInfoResponse.class);
    assertThat(actual, is(expected));
  }

}
