package com.example.library.application.service;

import com.example.library.domain.model.Book;
import java.util.List;

/**
 * 書籍情報の管理を行うサービスインタフェース.
 */
public interface LibraryService {
  
  /**
   * 指定した ID をもとに、書籍情報を取得します.
   * 
   * @param id 書籍 ID
   * @return 書籍ドメインモデルのインスタンス
   */
  public Book findBookById(int id);
  
  /**
   * 全書籍情報を取得します.
   * 
   * @return 書籍ドメインモデルのインスタンス群
   */
  public List<Book> getAllBooks();
  
}
