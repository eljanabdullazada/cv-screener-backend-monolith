package com.company.cvscreener.cv.service.impl;

import com.company.cvscreener.applicant.entity.Applicant;
import com.company.cvscreener.applicant.repository.ApplicantRepository;
import com.company.cvscreener.cv.extractor.PdfTextExtractor;
import com.company.cvscreener.cv.mongo.CvDocument;
import com.company.cvscreener.cv.mongo.CvMongoRepository;
import com.company.cvscreener.cv.service.CvService;
import com.company.cvscreener.cv.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CvServiceImpl implements CvService {

    private final ApplicantRepository applicantRepository;
    private final FileStorageService fileStorageService;
    private final PdfTextExtractor pdfTextExtractor;
    private final CvMongoRepository cvMongoRepository;

    @Override
    public void uploadCv(UUID applicationId, MultipartFile file) {

        Applicant applicant = applicantRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        String filePath = fileStorageService.saveCv(applicationId, file);

        String text = pdfTextExtractor.extractText(filePath);

        CvDocument document = CvDocument.builder()
                .applicantId(applicationId)
                .extractedText(text)
                .filePath(filePath)
                .build();

        cvMongoRepository.save(document);
    }
}