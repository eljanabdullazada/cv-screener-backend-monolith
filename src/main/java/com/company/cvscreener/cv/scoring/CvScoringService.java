package com.company.cvscreener.cv.scoring;

public interface CvScoringService {
    public double calculateScore(String requirements, String cvText);
}
