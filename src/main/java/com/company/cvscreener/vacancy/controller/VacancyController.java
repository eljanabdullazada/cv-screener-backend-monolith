package com.company.cvscreener.vacancy.controller;

import com.company.cvscreener.applicant.entity.Applicant;
import com.company.cvscreener.applicant.service.ApplicantService;
import com.company.cvscreener.vacancy.entity.Vacancy;
import com.company.cvscreener.vacancy.service.VacancyService;
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
    public ResponseEntity<Vacancy> create(@RequestBody Vacancy vacancy, Principal principal) {
        Vacancy saved = vacancyService.create(vacancy, principal.getName());
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
    public List<Vacancy> findAll() {
        return vacancyService.findAll();
    }

    // ANY AUTHENTICATED USER
    @GetMapping("/{id}")
    public ResponseEntity<Vacancy> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(vacancyService.findById(id));
    }

    @PreAuthorize("hasRole('CANDIDATE')")
    @PostMapping("/{id}/apply")
    public ResponseEntity<Applicant> apply(
            @PathVariable UUID id,
            Principal principal
    ) {
        return ResponseEntity.ok(applicantService.apply(id, principal.getName()));
    }
}
