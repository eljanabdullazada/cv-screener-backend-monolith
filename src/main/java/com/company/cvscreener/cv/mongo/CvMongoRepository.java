package com.company.cvscreener.cv.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface CvMongoRepository extends MongoRepository<CvDocument, String> {

    Optional<CvDocument> findByApplicantId(UUID applicantId);

}