package com.company.cvscreener.cv.extractor;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@Slf4j
public class PdfTextExtractor {

    public String extractText(String filePath) {

        try (PDDocument document = PDDocument.load(new File(filePath))) {

            PDFTextStripper stripper = new PDFTextStripper();

            return stripper.getText(document);

        } catch (IOException e) {

            log.error("Failed to extract text from CV {}", filePath);

            throw new RuntimeException("Could not read CV file", e);
        }
    }
}