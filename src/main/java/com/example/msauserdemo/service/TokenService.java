package com.example.msauserdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TokenService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 리플레시 토큰을 이메일을 통해서 레디스에서 획득
    @Bean
    public String getRefreshToken(String email) {
        return redisTemplate.opsForValue().get( email ); // 키:email, 값:토큰 -> 만료되면 삭제됨
    }
    // 리플레시 토큰 레디스에 저장
    @Bean
    public void saveRefreshToken(String email, String refreshToken) {
        redisTemplate.opsForValue().set( email, refreshToken, Duration.ofDays(7));// 만료시간 7일
    }
    // 리플레시 토큰 삭제
    @Bean
    public void deleteRefreshToken(String email) {
        redisTemplate.delete( email );
    }
}

