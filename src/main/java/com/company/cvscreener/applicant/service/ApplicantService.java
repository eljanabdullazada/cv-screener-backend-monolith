package com.company.cvscreener.applicant.service;

import com.company.cvscreener.applicant.dto.ApplicantRankingResponseDTO;
import com.company.cvscreener.applicant.dto.ApplicantResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ApplicantService {
    ApplicantResponseDTO apply(UUID vacancyId, String username);
    List<ApplicantRankingResponseDTO> getApplicantRanking(UUID vacancyId);
    void approveApplication(UUID applicationId);
    void rejectApplication(UUID applicationId);
    void deleteApplication(UUID applicationId);
}
