package com.squad.gateway.exception.custom;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GatewayException extends RuntimeException {

  public GatewayException(String message) {
    super(message);
    this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
  }

  public GatewayException(String message, Exception exception) {
    super(message, exception);
    this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
  }

  public GatewayException(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

  private final HttpStatus httpStatus;
}
