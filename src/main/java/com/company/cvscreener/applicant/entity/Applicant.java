package com.company.cvscreener.applicant.entity;

import com.company.cvscreener.auth.domain.User;
import com.company.cvscreener.vacancy.entity.Vacancy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "applicants",
        indexes = {
                @Index(name = "idx_applicants_vacancy", columnList = "vacancy_id"),
                @Index(name = "idx_applicants_user", columnList = "user_id"),
                @Index(name = "idx_applicants_score_desc", columnList = "score DESC")
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_vacancy",
                        columnNames = {"user_id", "vacancy_id"}
                )
        }
)
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // many applications belong to one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // many applications belong to one vacancy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacancy_id", nullable = false)
    private Vacancy vacancy;

    @Column(precision = 5, scale = 2) // this will be decimal (5, 2)
    private BigDecimal score;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    @Column(name = "applied_at", nullable = false, updatable = false)
    private LocalDateTime appliedAt;

    @Column(nullable = false)
    private boolean deleted = false;

    @PrePersist
    protected void onApply() {
        this.appliedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = ApplicationStatus.NEW;
        }
    }

}
