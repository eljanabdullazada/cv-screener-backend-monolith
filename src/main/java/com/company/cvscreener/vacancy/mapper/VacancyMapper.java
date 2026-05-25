package com.company.cvscreener.vacancy.mapper;

import com.company.cvscreener.auth.domain.User;
import com.company.cvscreener.vacancy.dto.UserSummaryDTO;
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

        User user = vacancy.getCreatedBy();
        UserSummaryDTO createdBy = new UserSummaryDTO(user.getUsername());

        return new VacancyResponseDTO(
                vacancy.getId(),
                vacancy.getTitle(),
                vacancy.getDescription(),
                vacancy.getRequirements(),
                createdBy,
                vacancy.getActive(),
                vacancy.getCreatedAt(),
                vacancy.getClosedAt()
        );
    }
}
