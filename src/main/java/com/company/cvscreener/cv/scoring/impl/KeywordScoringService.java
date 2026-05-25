package com.company.cvscreener.cv.scoring.impl;

import com.company.cvscreener.cv.scoring.CvScoringService;
import jakarta.servlet.Filter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class KeywordScoringService implements CvScoringService {

    @Override
    public double calculateScore(String requirements, String cvText){
        List<String> requirementKeywords = Arrays.stream(requirements.split("\\s+"))
                .map(String::toLowerCase)
                .toList();

        String normalizedCv = cvText.toLowerCase();

        long matchedCount = requirementKeywords.stream()
                .filter(normalizedCv::contains)
                .count();

        return ((double) matchedCount / requirementKeywords.size()) * 100;
    }
}
