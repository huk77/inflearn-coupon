package com.example.api.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisUserRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public Long save(String userId) {
        return redisTemplate.opsForSet().add("save_user", userId);
    }
}
