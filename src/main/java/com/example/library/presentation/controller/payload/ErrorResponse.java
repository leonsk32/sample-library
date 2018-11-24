package com.example.library.presentation.controller.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * エラーを表すペイロード.
 */
@Value
@AllArgsConstructor
public class ErrorResponse {

  @JsonProperty("error_code")
  private String errorCode;
  
  @JsonProperty("error_message")
  private String errorMessage;
  
}
