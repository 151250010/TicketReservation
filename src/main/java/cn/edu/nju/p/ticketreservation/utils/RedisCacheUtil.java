package cn.edu.nju.p.ticketreservation.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheUtil {

//    public static final String CACHE_NAME = "cache";
    public static final int CACHE_TIME = 60; // 60 seconds

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void putCache(String key, String value) {
        final byte[] bKey = key.getBytes();
        final byte[] bValue = value.getBytes();
        redisTemplate.execute((RedisCallback<Object>) redisConnection -> redisConnection.setEx(bKey, CACHE_TIME, bValue)); // set with existing time
    }

    public String getCache(String key) {
        assert key != null;
        byte[] result = redisTemplate.execute((RedisCallback<byte[]>) redisConnection -> redisConnection.get(key.getBytes()));
        return result == null ? null : new String(result);
    }

    public boolean cacheExist(String key) {
        return redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> redisConnection.exists(key.getBytes()));
    }
}
