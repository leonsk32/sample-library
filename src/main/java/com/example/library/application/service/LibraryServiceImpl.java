package com.example.library.application.service;

import com.example.library.common.exception.BookNotFoundException;
import com.example.library.domain.model.Book;
import com.example.library.infrastructure.datasource.entity.BookEntity;
import com.example.library.infrastructure.datasource.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link LibraryService} の実装クラス.
 */
@Service
public class LibraryServiceImpl implements LibraryService {
  
  @Autowired
  private BookRepository repository;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Book findBookById(int id) {
    
    Optional<BookEntity> result = repository.findById(id);
    if (result.isEmpty()) {
      throw new BookNotFoundException();
    }
    
    return result.get().createBookInstance();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Book> getAllBooks() {
    return repository.findAll().stream()
        .map(BookEntity::createBookInstance)
        .collect(Collectors.toList());
  }

  /**
   * {@inheritDoc}}
   */
  @Override
  public Book createBook(Book book) {
    BookEntity registered = repository.save(new BookEntity(book));
    return registered.createBookInstance();
  }

}
