package com.example.library.infrastructure.datasource.entity;

import com.example.library.domain.model.Book;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 書籍情報エンティティ.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "title")
  private String title;

  @Column(name = "price")
  private int price;

  @Column(name = "stock")
  private int stock;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "borrowers", joinColumns = @JoinColumn(name = "book_id"))
  @Column(name = "name")
  private List<String> borrowers;
  
  /**
   * コンストラクタ.
   * 
   * @param book {@link Book} ドメインクラスのインスタンス
   */
  public BookEntity(Book book) {
    this.id = book.getId();
    this.title = book.getTitle();
    this.price = book.getPrice();
    this.stock = book.getStock();
    this.borrowers = book.getBorrowers();
  }
  
  /**
   * このクラスの情報を使用してドメインのクラスを作成します.
   * 
   * @return {@link Book} ドメインクラスのインスタンス
   */
  public Book createBookInstance() {
    return new Book(id, title, price, stock, borrowers);
  }
}
