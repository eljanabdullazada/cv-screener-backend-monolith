package com.company.cvscreener.vacancy.repository;

import com.company.cvscreener.vacancy.entity.Vacancy;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VacancyRepository extends JpaRepository<Vacancy, UUID> {

    Optional<Vacancy> findByIdAndActiveTrue(UUID id);
    List<Vacancy> findAllByActiveTrue();
    @EntityGraph(attributePaths = {"createdBy", "createdBy.roles"})
    List<Vacancy> findAll();

}
