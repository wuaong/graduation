package com.wqz.alumniBook.service.Impl;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service

public class TokenService {
    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;
    public String checkLogin(String token) {
        String redisValue = stringRedisTemplate.opsForValue().get(token);
        if (Strings.isNullOrEmpty(redisValue)){
            return null;
        }else {
            return redisValue;
        }
    }
}
