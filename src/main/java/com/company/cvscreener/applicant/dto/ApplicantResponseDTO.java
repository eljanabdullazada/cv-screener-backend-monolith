package com.company.cvscreener.applicant.dto;

import com.company.cvscreener.applicant.entity.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApplicantResponseDTO (
        UUID id,
        UUID vacancyId,
        String vacancyTitle,
        ApplicationStatus status,
        LocalDateTime appliedAt
) {}
