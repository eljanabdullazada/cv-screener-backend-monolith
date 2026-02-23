package com.company.cvscreener.applicant.repository;

import com.company.cvscreener.applicant.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApplicantRepository extends JpaRepository<Applicant, UUID> {

    Optional<Applicant> findByUserIdAndVacancyIdAndDeletedFalse(UUID userId, UUID vacancyId);

    List<Applicant> findAllByVacancyIdAndDeletedFalseOrderByScoreDesc(UUID vacancyId);

}
