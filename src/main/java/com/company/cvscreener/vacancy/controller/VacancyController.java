package com.company.cvscreener.vacancy.controller;

import com.company.cvscreener.applicant.dto.ApplicantRankingResponseDTO;
import com.company.cvscreener.applicant.dto.ApplicantResponseDTO;
import com.company.cvscreener.applicant.service.ApplicantService;
import com.company.cvscreener.vacancy.dto.VacancyRequestDTO;
import com.company.cvscreener.vacancy.dto.VacancyResponseDTO;
import com.company.cvscreener.vacancy.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;
    private final ApplicantService applicantService;

    // HR ONLY
    @PreAuthorize("hasRole('HR')")
    @PostMapping
    public ResponseEntity<VacancyResponseDTO> create(@Valid @RequestBody VacancyRequestDTO vacancy) {
        VacancyResponseDTO saved = vacancyService.create(vacancy);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // HR ONLY
    @PreAuthorize("hasRole('HR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable UUID id) {
      vacancyService.delete(id);
      return ResponseEntity.noContent().build();
    }

    // HR ONLY
    @PreAuthorize("hasRole('HR')")
    @GetMapping("/{vacancyId}/ranking")
    public ResponseEntity<List<ApplicantRankingResponseDTO>> getRanking(@Valid @PathVariable UUID vacancyId) {
        return ResponseEntity.ok(applicantService.getApplicantRanking(vacancyId));
    }

    // ANY AUTHENTICATED USER
    @GetMapping
    public List<VacancyResponseDTO> findAll() {
        return vacancyService.findAll();
    }

    // ANY AUTHENTICATED USER
    @GetMapping("/{id}")
    public ResponseEntity<VacancyResponseDTO> findById(@Valid @PathVariable UUID id) {
        return ResponseEntity.ok(vacancyService.findById(id));
    }

    // CANDIDATE ONLY
    @PreAuthorize("hasRole('CANDIDATE')")
    @PostMapping("/{id}/apply")
    public ResponseEntity<ApplicantResponseDTO> apply(@Valid
            @PathVariable UUID id,
            Principal principal
    ) {
        return ResponseEntity.ok(applicantService.apply(id, principal.getName()));
    }
}
