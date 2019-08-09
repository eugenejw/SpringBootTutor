package com.example.demo.service;

//
//import com.mongodb.MongoClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
////import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.stereotype.Component;
//
//import java.net.UnknownHostException;

//@Configuration
public class RedisConfig {

//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//
//        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
//        connectionFactory.setUsePool(true);
//        connectionFactory.setHostName("localhost");
//        connectionFactory.setPort(6379);
//
//        return connectionFactory;
//    }
//
//    @Bean("esClusterTemplate")
//    public RedisTemplate<String, String> esClusterTemplate(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<String, String> template = new RedisTemplate<>();
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer());
//        template.setDefaultSerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(new StringRedisSerializer());
//        template.setConnectionFactory(connectionFactory);
//        return template;
//    }
}
