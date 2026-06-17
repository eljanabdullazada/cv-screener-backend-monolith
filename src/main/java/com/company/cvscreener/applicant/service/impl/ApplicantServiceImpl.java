package com.company.cvscreener.applicant.service.impl;

import com.company.cvscreener.applicant.dto.ApplicantRankingResponseDTO;
import com.company.cvscreener.applicant.dto.ApplicantResponseDTO;
import com.company.cvscreener.applicant.entity.Applicant;
import com.company.cvscreener.applicant.repository.ApplicantRepository;
import com.company.cvscreener.applicant.service.ApplicantService;
import com.company.cvscreener.auth.domain.User;
import com.company.cvscreener.auth.repository.UserRepository;
import com.company.cvscreener.common.exception.BusinessException;
import com.company.cvscreener.common.exception.ResourceNotFoundException;
import com.company.cvscreener.vacancy.entity.Vacancy;
import com.company.cvscreener.vacancy.repository.VacancyRepository;
import com.company.cvscreener.applicant.mapper.ApplicantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.company.cvscreener.applicant.entity.ApplicationStatus.APPROVED;
import static com.company.cvscreener.applicant.entity.ApplicationStatus.REJECTED;

@Service
@RequiredArgsConstructor
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final UserRepository userRepository;
    private final VacancyRepository vacancyRepository;

    @Override
    @Transactional
    public ApplicantResponseDTO apply(UUID vacancyId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new ResourceNotFoundException("Vacancy not found!"));

        if (!vacancy.getActive()) {
            throw new BusinessException("This vacancy is no longer active!");
        }

        applicantRepository.findByUserIdAndVacancyIdAndDeletedFalse(user.getId(), vacancyId)
                .ifPresent(a -> {
                    throw new BusinessException("You have already applied for this vacancy!");
                });

        Applicant applicant = ApplicantMapper.toEntity(user, vacancy);
        applicantRepository.save(applicant);

        return ApplicantMapper.toResponseDto(applicant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicantRankingResponseDTO> getApplicantRanking(UUID vacancyId) {
        return ApplicantMapper.toRankingResponseDtoList(applicantRepository.findAllByVacancyIdAndDeletedFalseOrderByScoreDesc(vacancyId));
    }

    @Override
    @Transactional
    public void approveApplication(UUID applicationId) {
        Applicant applicant = applicantRepository.findById(applicationId).
                orElseThrow(() -> new ResourceNotFoundException("Application not found with ID: " + applicationId));
        if (applicant.getStatus() == APPROVED || Boolean.TRUE.equals(applicant.getDeleted())) {
            throw new BusinessException("This application is deleted or already approved!");
        }
        applicant.setStatus(APPROVED);
    }

    @Override
    @Transactional
    public void rejectApplication(UUID applicationId) {
        Applicant applicant = applicantRepository.findById(applicationId).
                orElseThrow(() -> new ResourceNotFoundException("Application not found!"));
        if (applicant.getStatus() == REJECTED || Boolean.TRUE.equals(applicant.getDeleted())) {
            throw new BusinessException("This application is deleted or already rejected!");
        }
        applicant.setStatus(REJECTED);
    }

    @Override
    @Transactional
    public void deleteApplication(UUID applicationId) {
        Applicant applicant = applicantRepository.findById(applicationId).
                orElseThrow(() -> new ResourceNotFoundException("Application not found!"));
        if (applicant.getStatus() == APPROVED) {
            throw new BusinessException("Approved applications cannot be deleted!");
        }

        if (Boolean.TRUE.equals(applicant.getDeleted())) {
            throw new BusinessException("Applications cannot be deleted twice!");
        }
        applicant.setDeleted(true);
    }
}
