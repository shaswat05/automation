package com.test.automation.test;

import com.test.automation.redis.RedisHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RedisTest {

    private final RedisHelper redis = new RedisHelper("http://localhost:6379");

    @Test
    public void testStrings() {
        String key = "string";
        String value = "Automation";
        redis.delete(key);
        redis.setString(key, value);
        Assert.assertEquals(redis.getString(key), value, "Value mismatch for key " + key);
    }

    @Test
    public void testLists() {
        String key = "list";
        redis.delete(key);
        List<String> values = Arrays.asList("Test", "Automation", "For", "Redis");
        for (String value : values) {
            System.out.println(value);
            redis.rpush(key, value);
        }
        Assert.assertEquals(redis.lpop(key), values.getFirst(), "Value mismatch 1");
        Assert.assertEquals(redis.lpop(key), values.get(1), "Value mismatch 2");
        Assert.assertEquals(redis.rpop(key), values.getLast(), "Value mismatch 3");
        Assert.assertEquals(redis.rpop(key), values.get(values.size() - 2), "Value mismatch 4");
    }


    @Test
    public void testSets() {
        String key = "set";
        List<String> values = Arrays.asList("Test", "Automation", "For", "Redis");
        redis.delete(key);
        for (String value : values) redis.sadd(key, value);
        for (String value : values)
            Assert.assertTrue(redis.sismember(key, value), value + " does not exist using sismember.");
        for (String value : values)
            Assert.assertTrue(redis.smembers(key).contains(value), value + " does not exist using smembers.");
    }

    @Test
    public void testHashes() {
        String key = "hash";
        redis.delete(key);
        Map<String, String> fieldValMap = Map.of("one", "1", "two", "2", "three", "3");

        for (Map.Entry<String, String> entry : fieldValMap.entrySet())
            redis.hset(key, entry.getKey(), entry.getValue());

        for (String field : fieldValMap.keySet())
            Assert.assertEquals(redis.hget(key, field), fieldValMap.get(field), field + " does not exist using HGET.");

        for (Map.Entry<String, String> entry : redis.hgetAll(key).entrySet())
            Assert.assertEquals(entry.getValue(), fieldValMap.get(entry.getKey()), entry.getKey() + " does not exist using HGETALL.");
    }


    @Test
    public void testSortedSets() {
        String key = "sortedSet";
        redis.delete(key);
        Map<Double, String> fieldValMap = Map.of(1d, "one", 2d, "two", 3d, "three");
        for (Map.Entry<Double, String> entry : fieldValMap.entrySet())
            redis.zadd(key, entry.getKey(), entry.getValue());

        List<String> actualValues = redis.zrange(key, 0, -1);
        for (String value : fieldValMap.values())
            Assert.assertTrue(actualValues.contains(value), value + " does not exists in sorted set.");
    }

}
