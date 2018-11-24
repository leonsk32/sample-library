package com.example.library.application.service;

import com.example.library.common.exception.BookNotFoundException;
import com.example.library.domain.model.Book;
import com.example.library.infrastructure.datasource.entity.BookEntity;
import com.example.library.infrastructure.datasource.repository.BookRepository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * {@link LibraryService} の実装クラス.
 */
@Service
@Transactional
public class LibraryServiceImpl implements LibraryService {
  
  @Autowired
  BookRepository repository;
  
  @Override
  public Book findBookById(int id) {
    
    Optional<BookEntity> result = repository.findById(id);
    if (result.isEmpty()) {
      throw new BookNotFoundException();
    }
    
    BookEntity entity = result.get();
    
    return new Book(
        id,
        entity.getTitle(),
        entity.getPrice(),
        entity.getStock(),
        entity.getBorrowers());
  }

}
