package com.example.library.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

/**
 * 書籍を表すドメインモデル.
 */
@Getter
@Value
@AllArgsConstructor
public class Book {
  private int id;
  private String title;
  private int price;
  private int stock;
  private List<String> borrowers;
}
