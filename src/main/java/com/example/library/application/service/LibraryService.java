package com.example.library.application.service;

import com.example.library.domain.model.Book;

/**
 * 書籍情報の管理を行うサービスインタフェース.
 */
public interface LibraryService {
  public Book findBookById(int id);
}
