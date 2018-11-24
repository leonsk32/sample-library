package com.example.library.application.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.library.common.exception.BookNotFoundException;
import com.example.library.domain.model.Book;
import com.example.library.infrastructure.datasource.entity.BookEntity;
import com.example.library.infrastructure.datasource.repository.BookRepository;

public class LibraryServiceTest {
  
  @InjectMocks
  LibraryServiceImpl service;
  
  @Mock
  BookRepository repository;
  
  @Rule
  public ExpectedException exception = ExpectedException.none();
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void test_findBookById_指定したIDの書籍を取得できる() {
    
    BookEntity mockResponse = new BookEntity(5, "Sample Book A", 1500, 3, Arrays.asList("taro", "jiro"));
    when(repository.findById(5)).thenReturn(Optional.of(mockResponse));
    
    Book expected = new Book(5, "Sample Book A", 1500, 3, Arrays.asList("taro", "jiro"));
    Book actual = service.findBookById(5);
    
    assertThat(actual, is(expected));
  }
  
  @Test
  public void test_findBookById_指定したIDの書籍が存在せずBookNotFoundExceptionが発生する() {
    when(repository.findById(3)).thenReturn(Optional.empty());
    
    exception.expect(BookNotFoundException.class);
    service.findBookById(3);
    
    fail("no exception.");
  }

}
