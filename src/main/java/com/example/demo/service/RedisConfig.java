package com.example.demo.service;


import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

@Configuration
@PropertySource("application.properties")
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisbHost;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setUsePool(true);
        connectionFactory.setHostName(redisbHost);
        connectionFactory.setPort(6379);

        return connectionFactory;
    }

    @Bean("esClusterTemplate")
    public RedisTemplate<String, String> esClusterTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setDefaultSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
