package com.company.cvscreener.cv.storage;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileStorageService {

    String saveCv(UUID applicationId, MultipartFile file);

}
