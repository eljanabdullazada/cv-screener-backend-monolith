package com.company.cvscreener.vacancy.dto;

import com.company.cvscreener.auth.domain.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record VacancyResponseDTO(
        UUID id,
        String title,
        String description,
        String requirements,
        User createdByUsername,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime closedAt
) {}

