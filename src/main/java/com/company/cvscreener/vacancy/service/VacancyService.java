package com.company.cvscreener.vacancy.service;

import com.company.cvscreener.vacancy.dto.VacancyRequestDTO;
import com.company.cvscreener.vacancy.dto.VacancyResponseDTO;
import com.company.cvscreener.vacancy.entity.Vacancy;

import java.util.List;
import java.util.UUID;

public interface VacancyService{
    VacancyResponseDTO create(VacancyRequestDTO dto);
    void delete(UUID id);
    List<VacancyResponseDTO> findAll();
    VacancyResponseDTO findById(UUID id);
}
