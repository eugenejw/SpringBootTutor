package com.example.demo.service;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

@Configuration
@PropertySource("application.properties")
public class SimpleMongoConfig {

    @Value("${spring.data.mongodb.host}")
    private String mongodbHost;

    @Bean
    public MongoClient testMongo() {
        return new MongoClient(mongodbHost);
    }

    @Bean("indexMgrMongo")
    public MongoTemplate mongoTemplate() throws Exception {
        try {
            return new MongoTemplate(testMongo(), "CrossClusterTenantMapping");
        } catch (Exception e){

        }

        return null;

    }

}
