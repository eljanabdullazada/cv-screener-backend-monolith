package com.company.cvscreener.cv.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface CvService {

    void uploadCv(UUID applicationId, MultipartFile file);

}