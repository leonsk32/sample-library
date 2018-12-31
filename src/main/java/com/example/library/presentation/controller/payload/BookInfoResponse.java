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
   * コンストラクタ.
   * 
   * @param book {@link Book} ドメインクラスのインスタンス
   */
  public BookInfoResponse(Book book) {
    this.bookId = book.getId();
    this.title = book.getTitle();
    this.price = book.getPrice();
    this.stock = book.getStock();
    this.borrowers = book.getBorrowers();
  }
}
