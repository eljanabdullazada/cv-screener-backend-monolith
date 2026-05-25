package com.company.cvscreener.vacancy.controller;

import com.company.cvscreener.applicant.dto.ApplicantResponseDTO;
import com.company.cvscreener.applicant.entity.Applicant;
import com.company.cvscreener.applicant.service.ApplicantService;
import com.company.cvscreener.vacancy.dto.VacancyRequestDTO;
import com.company.cvscreener.vacancy.dto.VacancyResponseDTO;
import com.company.cvscreener.vacancy.entity.Vacancy;
import com.company.cvscreener.vacancy.service.VacancyService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<VacancyResponseDTO> create(@RequestBody @NotNull VacancyRequestDTO vacancy) {
        VacancyResponseDTO saved = vacancyService.create(vacancy);
        return ResponseEntity.ok(saved);
    }

    // HR ONLY
    @PreAuthorize("hasRole('HR')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        vacancyService.delete(id);
    }

    // ANY AUTHENTICATED USER
    @GetMapping
    public List<VacancyResponseDTO> findAll() {
        return vacancyService.findAll();
    }

    // ANY AUTHENTICATED USER
    @GetMapping("/{id}")
    public ResponseEntity<VacancyResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(vacancyService.findById(id));
    }

    @PreAuthorize("hasRole('CANDIDATE')")
    @PostMapping("/{id}/apply")
    public ResponseEntity<ApplicantResponseDTO> apply(
            @PathVariable UUID id,
            Principal principal
    ) {
        return ResponseEntity.ok(applicantService.apply(id, principal.getName()));
    }
}
