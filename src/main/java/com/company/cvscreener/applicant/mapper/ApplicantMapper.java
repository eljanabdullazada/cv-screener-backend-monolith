package com.company.cvscreener.applicant.mapper;

import com.company.cvscreener.applicant.dto.ApplicantResponseDTO;
import com.company.cvscreener.applicant.entity.Applicant;


public class ApplicantMapper {
    public static ApplicantResponseDTO toResponseDto(Applicant applicant) {

        return new ApplicantResponseDTO(
                applicant.getId(),
                applicant.getVacancy().getId(),
                applicant.getVacancy().getTitle(),
                applicant.getStatus(),
                applicant.getAppliedAt()
        );
    }
}