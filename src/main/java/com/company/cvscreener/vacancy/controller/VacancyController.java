package com.company.cvscreener.vacancy.controller;

import com.company.cvscreener.vacancy.entity.Vacancy;
import com.company.cvscreener.vacancy.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;

    // HR ONLY
    @PreAuthorize("hasRole('HR')")
    @PostMapping
    public Vacancy create(@RequestBody Vacancy vacancy) {
        return vacancyService.create(vacancy);
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
}