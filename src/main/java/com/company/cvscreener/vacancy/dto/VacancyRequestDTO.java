package com.company.cvscreener.vacancy.dto;

import jakarta.validation.constraints.NotNull;

public record VacancyRequestDTO(
        @NotNull(message = "Title of the vacancy cannot be null.")
        String title,
        @NotNull(message = "Description of the vacancy cannot be null.")
        String description,
        @NotNull(message = "Requirements of the vacancy cannot be null.")
        String requirements
) {}
