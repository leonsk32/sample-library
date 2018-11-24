package com.example.library.presentation.controller;

import com.example.library.common.exception.BookNotFoundException;
import com.example.library.presentation.controller.payload.ErrorResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Controller を横断して例外をハンドリングするクラス.
 */
@ControllerAdvice
public class LibraryExceptionHandler {
  
  /**
   * {@link BookNotFoundException} が発生した場合に、エラーレスポンスを返却します.
   * 
   * @param request HTTP リクエスト
   * @param e {@link BookNotFoundException}
   * @return エラーレスポンス
   */
  @ExceptionHandler(BookNotFoundException.class)
  public ResponseEntity<ErrorResponse> getException(HttpServletRequest request,
                                                    BookNotFoundException e) {
    return new ResponseEntity<>(new ErrorResponse("0001", "book not found."),
                                HttpStatus.NOT_FOUND);
  }
}
