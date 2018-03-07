package cn.edu.nju.p.ticketreservation.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RedisCacheUtil {

//    public static final String CACHE_NAME = "cache";
    private static final int CACHE_TIME = 120; // 60 seconds

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

    public <T> void putCache(String key, T obj) {

        final byte[] bKey = key.getBytes();
        final byte[] bValue = ProtoStuffSerializerUtil.serialize(obj);
        redisTemplate.execute((RedisCallback<Object>) redisConnection -> redisConnection.setNX(bKey, bValue));
    }

    public <T> void putCacheWithExpireTime(String key, T obj, final long expireTime) {

        final byte[] bKey =key.getBytes();
        final byte[] bValue = ProtoStuffSerializerUtil.serialize(obj);
        redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            redisConnection.setEx(bKey, expireTime, bValue);
            return true;
        });
    }

    public <T> T getCache(final String key, Class<T> targetClass) {

        byte[] result = redisTemplate.execute((RedisCallback<byte[]>) redisConnection -> redisConnection.get(key.getBytes()));
        return result == null ? null : ProtoStuffSerializerUtil.deSerialize(result, targetClass);
    }

    public void deleteCacheWithPattern(String pattern) {

        Set<String> keys = redisTemplate.keys(pattern);
        keys.forEach(key-> redisTemplate.execute((RedisCallback<Object>) redisConnection -> redisConnection.del(key.getBytes())));
    }

    public void deleteAllCache() {
        deleteCacheWithPattern("*");
    }
}
