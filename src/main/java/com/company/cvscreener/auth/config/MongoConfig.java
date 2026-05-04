package com.company.cvscreener.auth.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;


@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "cvscreener";
    }

    @Override
    @Bean
    public MongoClientSettings mongoClientSettings() {

        return MongoClientSettings.builder()
                .applyConnectionString(
                        new ConnectionString("mongodb://localhost:27017/cvscreener")
                )
                .uuidRepresentation(org.bson.UuidRepresentation.STANDARD)
                .build();
    }
}