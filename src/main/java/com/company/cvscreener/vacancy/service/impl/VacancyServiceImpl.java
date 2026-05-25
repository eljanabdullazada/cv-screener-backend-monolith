package com.company.cvscreener.vacancy.service.impl;

import com.company.cvscreener.auth.domain.User;
import com.company.cvscreener.auth.repository.UserRepository;
import com.company.cvscreener.vacancy.dto.VacancyRequestDTO;
import com.company.cvscreener.vacancy.dto.VacancyResponseDTO;
import com.company.cvscreener.vacancy.entity.Vacancy;
import com.company.cvscreener.vacancy.mapper.VacancyMapper;
import com.company.cvscreener.vacancy.repository.VacancyRepository;
import com.company.cvscreener.vacancy.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.company.cvscreener.vacancy.mapper.VacancyMapper.toEntity;
import static com.company.cvscreener.vacancy.mapper.VacancyMapper.toResponseDto;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;
    private final UserRepository userRepository;

    public VacancyResponseDTO create(VacancyRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Value of the dto cannot be null.");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new IllegalArgumentException("User must be authenticated to create a new Vacancy.");
        }

        String hrUsername = authentication.getName();

        User user = userRepository.findByUsername(hrUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Vacancy vacancy = toEntity(dto, user);
        vacancyRepository.save(vacancy);

        return toResponseDto(vacancy);
    }

    public void delete(UUID id) {
        vacancyRepository.deleteById(id);
    }

    public List<VacancyResponseDTO> findAll() {
        return vacancyRepository.findAll().stream()
                .map(VacancyMapper::toResponseDto)
                .toList();
    }

    public VacancyResponseDTO findById(UUID id){
        Vacancy vacancy = vacancyRepository.findByIdAndActiveTrue(id).
                orElseThrow(() -> new RuntimeException("Vacancy Not Found"));
        return toResponseDto(vacancy);
    }
}
