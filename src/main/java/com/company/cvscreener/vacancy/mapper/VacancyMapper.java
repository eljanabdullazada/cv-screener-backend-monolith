package com.company.cvscreener.vacancy.mapper;

import com.company.cvscreener.auth.domain.User;
import com.company.cvscreener.vacancy.dto.VacancyRequestDTO;
import com.company.cvscreener.vacancy.dto.VacancyResponseDTO;
import com.company.cvscreener.vacancy.entity.Vacancy;

public class VacancyMapper {

    public static Vacancy toEntity(VacancyRequestDTO dto, User hrUser) {
        return Vacancy.builder()
                .title(dto.title())
                .description(dto.description())
                .requirements(dto.requirements())
                .createdBy(hrUser)
                .active(true)
                .build();
    }

    public static VacancyResponseDTO toResponseDto(Vacancy vacancy) {
        return new VacancyResponseDTO(
                vacancy.getId(),
                vacancy.getTitle(),
                vacancy.getDescription(),
                vacancy.getRequirements(),
                vacancy.getCreatedBy(),
                vacancy.getActive(),
                vacancy.getCreatedAt(),
                vacancy.getClosedAt()
        );
    }
}
