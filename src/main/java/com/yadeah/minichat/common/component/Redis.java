package com.yadeah.minichat.common.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Redis {

    @Autowired
    private static RedisTemplate<String, String> redis;

    private static final String KEY_PREFIX = "minichat:%s";

    // region set

    /***
     * set
     * @param key 主键
     * @param value 值（String）
     */
    public void set(String key, String value) {
        redis.opsForValue().set(getKey(key), value);
    }

    /***
     * set
     * @param key 主键
     * @param value 值（String)
     * @param seconds 过期时间（秒）
     */
    public void set(String key, String value, long seconds) {
        set(key, value, seconds, TimeUnit.SECONDS);
    }

    /***
     * set
     * @param key 主键
     * @param value 值（String)
     * @param time 过期时间
     * @param timeUnit 过期时间单位
     */
    public void set(String key, String value, long time, TimeUnit timeUnit) {
        redis.opsForValue().set(getKey(key), value, time, timeUnit);
    }
    // endregion

    // region get

    /***
     * get
     * @param key 主键
     * @return String
     */
    public String get(String key) {
        return redis.opsForValue().get(getKey(key));
    }
    // endregion

    // region get and set
    public String getAndSet(String key, String value) {
        return redis.opsForValue().getAndSet(key, value);
    }
    public String getAndSet(String key, String value, long seconds) {
        String ret = redis.opsForValue().getAndSet(getKey(key), value);
        this.set(getKey(key), value, seconds);
        return ret;
    }
    // endregion

    // region delete

    /***
     * delete
     * @param key 主键
     */
    public void delete(String key) {
        redis.delete(getKey(key));
    }
    // endregion

    private String getKey(String key) {
        return String.format(KEY_PREFIX, key);
    }

}
