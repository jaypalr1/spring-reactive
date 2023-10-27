package com.squad.gateway.exception;

import com.squad.gateway.exception.custom.GatewayException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception exception) {
    log.info("Error stack: ", exception);

    return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(GatewayException.class)
  public ResponseEntity<String> handleException(GatewayException gatewayException) {
    log.info("Error stack: ", gatewayException);

    return new ResponseEntity<>(gatewayException.getMessage(), gatewayException.getHttpStatus());
  }
}
