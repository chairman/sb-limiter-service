package com.tx.lua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import java.util.Collections;


@Component
public class MyLimit {
    private static final RedisScript<Boolean> SCRIPT_LIMIT = RedisScript.of(new ClassPathResource("scripts/redis_rate_limit.lua"), Boolean.class);

    @Autowired
    private RedisTemplate<String, Object> jsonRedisTemplate;

    public Boolean limit(String limitKey, long limitCount, long expireMs) {
        return jsonRedisTemplate.execute(SCRIPT_LIMIT, Collections.singletonList(limitKey), limitCount, expireMs);
    }
}
