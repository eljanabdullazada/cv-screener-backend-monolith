package com.company.cvscreener.vacancy.entity;

import com.company.cvscreener.auth.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "vacancies",
        indexes = {
                @Index(name = "idx_vacancies_active", columnList = "active"),
                @Index(name = "idx_vacancies_created_by", columnList = "created_by")
        }
)
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String requirements;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private User createdBy;

    @Column(nullable = false)
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean active = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

}
