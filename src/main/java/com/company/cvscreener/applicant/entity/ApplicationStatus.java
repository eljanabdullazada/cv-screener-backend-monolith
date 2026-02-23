package com.company.cvscreener.applicant.entity;

public enum ApplicationStatus {

    NEW("Application submitted"),
    APPROVED("Candidate approved by HR"),
    REJECTED("Candidate rejected by HR");

    private final String description;

    ApplicationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}