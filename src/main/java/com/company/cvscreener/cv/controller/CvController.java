package com.company.cvscreener.cv.controller;

import com.company.cvscreener.cv.service.CvService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class CvController {

    private final CvService cvService;

    @PreAuthorize("hasRole('CANDIDATE')")
    @PostMapping("/{applicationId}/cv")
    public void uploadCv(
            @PathVariable UUID applicationId,
            @RequestParam("file") MultipartFile file
    ) {
        cvService.uploadCv(applicationId, file);
    }
}