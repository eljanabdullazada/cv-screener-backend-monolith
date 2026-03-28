package com.company.cvscreener.applicant.service.impl;

import com.company.cvscreener.applicant.entity.Applicant;
import com.company.cvscreener.applicant.entity.ApplicationStatus;
import com.company.cvscreener.applicant.repository.ApplicantRepository;
import com.company.cvscreener.applicant.service.ApplicantService;
import com.company.cvscreener.auth.domain.User;
import com.company.cvscreener.auth.repository.UserRepository;
import com.company.cvscreener.vacancy.entity.Vacancy;
import com.company.cvscreener.vacancy.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final UserRepository userRepository;
    private final VacancyRepository vacancyRepository;

    @Override
    @Transactional
    public Applicant apply(UUID vacancyId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));

        if (!vacancy.getActive()) {
            throw new RuntimeException("This vacancy is no longer active");
        }

        applicantRepository.findByUserIdAndVacancyIdAndDeletedFalse(user.getId(), vacancyId)
                .ifPresent(a -> {
                    throw new RuntimeException("You have already applied for this vacancy");
                });

        Applicant applicant = new Applicant();
        applicant.setUser(user);
        applicant.setVacancy(vacancy);
        applicant.setStatus(ApplicationStatus.NEW);
        applicant.setAppliedAt(LocalDateTime.now());
        applicant.setDeleted(false);

        return applicantRepository.save(applicant);
    }
}
