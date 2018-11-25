package com.example.library.presentation.controller.payload;

import com.example.library.domain.model.Book;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * 書籍情報を表すペイロード.
 */
@Value
@AllArgsConstructor
public class BookInfoResponse {
  
  @JsonProperty("book_id")
  private int bookId;
  private String title;
  private int price;
  private int stock;
  private List<String> borrowers;
  
  /**
   * {@link Book} ドメインモデルのインスタンスをもとに、ペイロードを作成します.
   * 
   * @param book 書籍ドメインモデルのインスタンス
   * @return 書籍情報を表すペイロード
   */
  public static BookInfoResponse createInstance(Book book) {
    return new BookInfoResponse(book.getId(),
                                book.getTitle(),
                                book.getPrice(),
                                book.getStock(),
                                book.getBorrowers());
  }
}
