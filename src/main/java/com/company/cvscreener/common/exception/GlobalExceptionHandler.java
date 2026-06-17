package com.company.cvscreener.common.exception;

import com.company.cvscreener.common.response.ApiResponse;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
    ApiResponse response = new ApiResponse(
          false
          , 404
          , e.getMessage()
          , LocalDateTime.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ApiResponse> handleBusinessException(BusinessException e) {
    ApiResponse response = new ApiResponse(
          false
          , 409
          , e.getMessage()
          , LocalDateTime.now()
    );
    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  // this one is going to prevent stack trace from being sent to the clients
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse> handleException(Exception e) {
    // Log the full stack trace for unexpected errors
    logger.error("Unexpected error occurred: ", e);

    ApiResponse response = new ApiResponse(
          false,
          500,
          "An unexpected error occurred. Please try again later.",
          LocalDateTime.now()
    );
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

}
