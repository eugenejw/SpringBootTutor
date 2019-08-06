package com.example.demo.service;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

@Configuration
public class SimpleMongoConfig {

    @Bean
    public MongoClient testMongo() {
        return new MongoClient("172.17.0.40");
    }

    @Bean("indexMgrMongo")
    public MongoTemplate mongoTemplate() throws Exception {
        try {
            return new MongoTemplate(testMongo(), "terbium_dev");
        } catch (Exception e){

        }

        return null;

    }
}
