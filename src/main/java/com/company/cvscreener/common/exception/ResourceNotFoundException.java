package com.company.cvscreener.common.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }
}

/*
Used for:
   User not found
   Vacancy not found
   Application not found
 */
