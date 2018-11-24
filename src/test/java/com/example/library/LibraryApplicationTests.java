package com.example.library;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.library.presentation.controller.payload.BookInfoResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryApplicationTests {
  
  private TestRestTemplate template;

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
    
    BookInfoResponse actual = template.getForObject("http://localhost:8080/library/api/books/1", BookInfoResponse.class);
    assertThat(actual, is(expected));
  }

}
