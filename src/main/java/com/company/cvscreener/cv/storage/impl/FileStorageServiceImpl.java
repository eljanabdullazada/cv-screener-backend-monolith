package com.company.cvscreener.cv.storage.impl;

import com.company.cvscreener.cv.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    private static final String UPLOAD_DIR = "uploads/cv/";

    @Override
    public String saveCv(UUID applicationId, MultipartFile file) {

        try {

            Files.createDirectories(Paths.get(UPLOAD_DIR));

            String filename = applicationId + ".pdf";

            Path filePath = Paths.get(UPLOAD_DIR, filename);

            Files.write(filePath, file.getBytes());

            return filePath.toString();

        } catch (IOException e) {
            throw new RuntimeException("Failed to store CV file", e);
        }

    }
}