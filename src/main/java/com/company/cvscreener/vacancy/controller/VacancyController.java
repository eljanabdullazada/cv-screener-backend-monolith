package com.company.cvscreener.vacancy.controller;

import com.company.cvscreener.auth.domain.User;
import com.company.cvscreener.auth.repository.UserRepository;
import com.company.cvscreener.vacancy.entity.Vacancy;
import com.company.cvscreener.vacancy.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;
    private final UserRepository userRepository;

    // HR ONLY
    @PreAuthorize("hasRole('HR')")
    @PostMapping
    public ResponseEntity<Vacancy> create(@RequestBody Vacancy vacancy, Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        vacancy.setCreatedBy(user);
        Vacancy saved = vacancyService.create(vacancy, user.getUsername());
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
}