package com.test.automation.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisHelper {

    private final Jedis redis;

    private RedisHelper(String url, JedisPoolConfig poolConfig) {
        JedisPool jedisPool = new JedisPool(poolConfig, url);
        this.redis = jedisPool.getResource();
    }

    public RedisHelper(String url) {
        this(url, PoolConfig.build());
    }


    /**
     * Strings
     * -------------------------------------------------------------
     */

    public void setString(String key, String value) {
        redis.set(key, value);
    }

    public String getString(String key) {
        return redis.get(key);
    }

    /**
     * Lists
     * -------------------------------------------------------------
     */

    public void lpush(String key, String value) {
        redis.lpush(key, value);
    }

    public void rpush(String key, String value) {
        redis.rpush(key, value);
    }

    public String rpop(String key) {
        return redis.rpop(key);
    }

    public String lpop(String key) {
        return redis.lpop(key);
    }

    /**
     * Sets
     * -------------------------------------------------------------
     */
    public void sadd(String key, String... members) {
        redis.sadd(key, members);
    }

    public Set<String> smembers(String key) {
        return redis.smembers(key);
    }

    public boolean sismember(String key, String member) {
        // This method tells if the given member exists for the key.
        return redis.sismember(key, member);
    }

    /**
     * Hashes
     * -------------------------------------------------------------
     */

    public void hset(String key, String field, String value) {
        redis.hset(key, field, value);
    }

    public String hget(String key, String field) {
        return redis.hget(key, field);
    }

    public Map<String, String> hgetAll(String key) {
        return redis.hgetAll(key);
    }

    /**
     * Sorted Sets
     * -------------------------------------------------------------
     */

    public void zadd(String key, double score, String member) {
        redis.zadd(key, score, member);
    }

    public List<String> zrange(String key, long start, long stop) {
        return redis.zrange(key, start, stop);
    }


    /**
     * Delete
     */

    public void delete(String key) {
        redis.del(key);
    }
}
