package com.example.library.presentation.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.library.application.service.LibraryService;
import com.example.library.common.exception.BookNotFoundException;
import com.example.library.domain.model.Book;
import com.example.library.infrastructure.datasource.entity.BookEntity;
import com.example.library.presentation.controller.payload.BookInfoRequest;
import com.example.library.presentation.controller.payload.BookInfoResponse;
import com.example.library.presentation.controller.payload.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LibraryControllerV1Test {

  @InjectMocks
  LibraryControllerV1 testTarget;

  @Mock
  LibraryService service;

  private MockMvc mvc;
  private ObjectMapper mapper;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mvc = MockMvcBuilders.standaloneSetup(testTarget)
                         .setControllerAdvice(LibraryExceptionHandler.class)
                         .build();
    mapper = new ObjectMapper();
  }

  @Test
  public void test_findBookById_指定したIDの書籍を取得できる() throws Exception {
    Book mockResponse = new Book(5, "Sample Book A", 1200, 3, Arrays.asList("taro", "jiro"));
    when(service.findBookById(5)).thenReturn(mockResponse);
    
    BookInfoResponse expected = new BookInfoResponse(
        5,
        "Sample Book A",
        1200,
        3,
        Arrays.asList("taro", "jiro"));
    
    mvc.perform(get("/api/v1/books/5").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
       .andExpect(status().isOk())
       .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
       .andExpect(content().json(mapper.writeValueAsString(expected)));
  }
  
  @Test
  public void test_findBookById_指定したIDの書籍が存在せずエラーレスポンスが返る() throws Exception {
    when(service.findBookById(3)).thenThrow(new BookNotFoundException());

    ErrorResponse expected = new ErrorResponse("0001", "book not found.");
    
    mvc.perform(get("/api/v1/books/3").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
       .andExpect(status().isNotFound())
       .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
       .andExpect(content().json(mapper.writeValueAsString(expected)));
  }
  
  @Test
  public void test_getAllBooks_全書籍を取得できる() throws Exception {
    List<Book> mockResponse = new ArrayList<>();
    mockResponse.add(new Book(1, "Sample Book A", 1500, 3, Arrays.asList("taro", "jiro")));
    mockResponse.add(new Book(2, "Sample Book B", 1600, 2, Arrays.asList("taro")));
    mockResponse.add(new Book(3, "Sample Book C", 1700, 1, Arrays.asList()));
    when(service.getAllBooks()).thenReturn(mockResponse);

    List<BookInfoResponse> expected = new ArrayList<>();
    expected.add(new BookInfoResponse(1, "Sample Book A", 1500, 3, Arrays.asList("taro", "jiro")));
    expected.add(new BookInfoResponse(2, "Sample Book B", 1600, 2, Arrays.asList("taro")));
    expected.add(new BookInfoResponse(3, "Sample Book C", 1700, 1, Arrays.asList()));
    
    mvc.perform(get("/api/v1/books").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
       .andExpect(status().isOk())
       .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
       .andExpect(content().json(mapper.writeValueAsString(expected)));
  }

  @Test
  public void test_createBook_書籍を登録できる() throws Exception {
    when(service.createBook(new Book(0, "Sample Book D", 1800, 4, Arrays.asList())))
      .thenReturn(new Book(4, "Sample Book D", 1800, 4, Arrays.asList()));
    
    BookInfoRequest request = new BookInfoRequest("Sample Book D", 1800, 4);
    BookInfoResponse expected = new BookInfoResponse(4, "Sample Book D", 1800, 4, Arrays.asList());
    
    mvc.perform(post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsString(request)))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
      .andExpect(content().json(mapper.writeValueAsString(expected)));
  }
  
  @Test
  public void test_createBook_必須項目が不足しておりエラーレスポンスが返る() throws Exception {
    fail("not implemented.");
  }
}
