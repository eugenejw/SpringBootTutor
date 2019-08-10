package com.example.demo.repository;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("RedisTest")
public class RedisTest {
    private String id;
    private String name;

    public RedisTest(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
