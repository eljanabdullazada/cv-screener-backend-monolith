package com.company.cvscreener.applicant.service;

import com.company.cvscreener.applicant.entity.Applicant;

import java.util.UUID;

public interface ApplicantService {
    Applicant apply(UUID vacancyId, String username);
}
