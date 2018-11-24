package com.example.library.infrastructure.datasource.repository;

import com.example.library.infrastructure.datasource.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 書籍情報を格納するリポジトリ.
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

}
