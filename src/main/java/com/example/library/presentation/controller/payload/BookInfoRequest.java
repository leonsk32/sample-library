package com.example.library.presentation.controller.payload;

import com.example.library.domain.model.Book;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 書籍登録情報を表すペイロード.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfoRequest {
  
  private String title;
  private int price;
  private int stock;
  
  /**
   * このクラスの情報を使用してドメインのクラスを作成します.
   * 
   * @return {@link Book} ドメインクラスのインスタンス
   */
  public Book createBookInstance() {
    return new Book(0, title, price, stock, Arrays.asList());
  }

}
