package com.example.library.infrastructure.datasource.entity;

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

}
