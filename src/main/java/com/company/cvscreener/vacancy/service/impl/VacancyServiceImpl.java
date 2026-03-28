package com.company.cvscreener.vacancy.service.impl;

import com.company.cvscreener.auth.domain.User;
import com.company.cvscreener.auth.repository.UserRepository;
import com.company.cvscreener.vacancy.entity.Vacancy;
import com.company.cvscreener.vacancy.repository.VacancyRepository;
import com.company.cvscreener.vacancy.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;
    private final UserRepository userRepository;

    public Vacancy create(Vacancy vacancy, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        vacancy.setCreatedBy(user);
        return vacancyRepository.save(vacancy);
    }

    public void delete(UUID id) {
        vacancyRepository.deleteById(id);
    }

    public List<Vacancy> findAll() {
        return vacancyRepository.findAll();
    }

    public Vacancy findById(UUID id){
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));
    }
}
