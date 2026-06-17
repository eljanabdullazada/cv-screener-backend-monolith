package com.company.cvscreener.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VacancyRequestDTO(

      @NotBlank(message = "Title cannot be blank.")
      @Size(max = 255, message = "Title cannot exceed 255 characters.")
      String title,

      @NotBlank(message = "Description cannot be blank.")
      String description,

      @NotBlank(message = "Requirements cannot be blank.")
      String requirements

) {}