package com.example.library.presentation.controller;

import com.example.library.application.service.LibraryService;
import com.example.library.domain.model.Book;
import com.example.library.presentation.controller.payload.BookInfoResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/book")
public class LibraryControllerV1 {

  @Autowired
  LibraryService service;
  
  /**
   * 指定された ID に対応する書籍情報を返却します.
   * 
   * @param id 書籍の ID
   * @return 書籍情報
   */
  @RequestMapping(method = RequestMethod.GET,
                  value = "{id}",
                  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public BookInfoResponse findBookById(@PathVariable String id) {
    Book book = service.findBookById(Integer.parseInt(id));
    
    BookInfoResponse response = 
        new BookInfoResponse(
              book.getId(),
              book.getTitle(),
              book.getPrice(),
              book.getStock(),
              book.getBorrowers());
    
    return response;
  }
}
