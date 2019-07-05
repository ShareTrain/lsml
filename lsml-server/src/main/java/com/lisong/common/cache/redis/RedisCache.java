package com.lisong.common.cache.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 缘起于高韩.
 *
 * <p>创建时间: <font style="color:#00FFFF">20181112 09:52</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Service
public class RedisCache {

    private static StringRedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        RedisCache.redisTemplate = redisTemplate;
    }

    public static void deleteInfo(String key) {
        if (redisTemplate.opsForValue().getOperations().hasKey(key)) {
            redisTemplate.opsForValue().getOperations().delete(String.valueOf(key));
            log.info("【缓存】删除缓存信息, key={}", key);
        } else {
            log.info("【缓存】删除缓存信息, 缓存不存在, key={}", key);
        }
    }
}
