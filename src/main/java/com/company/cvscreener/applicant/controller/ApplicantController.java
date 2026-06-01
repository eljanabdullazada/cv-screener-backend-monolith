package com.company.cvscreener.applicant.controller;

import com.company.cvscreener.applicant.service.ApplicantService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/applications")
public class ApplicantController {

    private final ApplicantService applicantService;

    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @PatchMapping("/{id}/approve")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<Void> approve(@PathVariable UUID id) {
        applicantService.approveApplication(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/reject")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<Void> reject(@PathVariable UUID id) {
        applicantService.rejectApplication(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        applicantService.deleteApplication(id);
        return ResponseEntity.ok().build();
    }
}
