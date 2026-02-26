package com.company.cvscreener.vacancy.service;

import com.company.cvscreener.vacancy.entity.Vacancy;

import java.util.List;
import java.util.UUID;

public interface VacancyService{
    Vacancy create(Vacancy vacancy);
    void delete(UUID id);
    List<Vacancy> findAll();
    Vacancy findById(UUID id);
}
