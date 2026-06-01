package com.company.cvscreener.applicant.mapper;

import com.company.cvscreener.applicant.dto.ApplicantRankingResponseDTO;
import com.company.cvscreener.applicant.dto.ApplicantResponseDTO;
import com.company.cvscreener.applicant.entity.Applicant;
import com.company.cvscreener.auth.domain.User;
import com.company.cvscreener.vacancy.entity.Vacancy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


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

    public static List<ApplicantRankingResponseDTO> toRankingResponseDtoList(List<Applicant> applicants) {
        if (applicants == null) {
            return Collections.emptyList();
        }

        return applicants.stream()
                .map(ApplicantMapper::toRankingResponseDto)
                .collect(Collectors.toList());
    }

    public static ApplicantRankingResponseDTO toRankingResponseDto(Applicant applicant) {
        return new ApplicantRankingResponseDTO(
                applicant.getId(),
                applicant.getUser().getUsername(),
                applicant.getUser().getEmail(),
                applicant.getScore(),
                applicant.getStatus(),
                applicant.getAppliedAt()
        );
    }

    public static Applicant toEntity(User user, Vacancy vacancy) {
        Applicant applicant = new Applicant();
        applicant.setUser(user);
        applicant.setVacancy(vacancy);
        applicant.setDeleted(false);
        return applicant;
    }
}