package com.company.cvscreener.common.response;

import java.time.LocalDateTime;

public record ApiResponse(
      boolean success,
      int status,
      String message,
      LocalDateTime timestamp
) {
}
