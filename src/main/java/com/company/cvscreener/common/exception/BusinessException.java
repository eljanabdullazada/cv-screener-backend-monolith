package com.company.cvscreener.common.exception;

public class BusinessException extends RuntimeException {
  public BusinessException(String message) {
    super(message);
  }
}

/*
Used for:
   Already applied
   Already approved
   Already deleted
   Approved application cannot be deleted
 */