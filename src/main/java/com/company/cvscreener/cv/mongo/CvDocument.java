package com.company.cvscreener.cv.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "cvs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CvDocument {

    @Id
    private String id;

    private UUID applicantId;

    private String extractedText;

    private String filePath;
}