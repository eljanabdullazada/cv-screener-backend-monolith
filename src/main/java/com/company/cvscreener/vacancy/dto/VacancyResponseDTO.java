package com.company.cvscreener.vacancy.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record VacancyResponseDTO(
        UUID id,
        String title,
        String description,
        String requirements,
        UserSummaryDTO createdBy,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime closedAt
) {}

