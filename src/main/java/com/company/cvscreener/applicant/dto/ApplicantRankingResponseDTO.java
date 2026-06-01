package com.company.cvscreener.applicant.dto;

import com.company.cvscreener.applicant.entity.ApplicationStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ApplicantRankingResponseDTO(
        UUID applicationId,
        String username,
        String email,
        BigDecimal score,
        ApplicationStatus status,
        LocalDateTime appliedAt
) {}