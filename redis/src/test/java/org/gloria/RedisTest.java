package org.gloria;

import org.gloria.service.CustomRedisTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * Create on 2016/12/8 14:29.
 *
 * @author : gloria.
 */
@EnableAutoConfiguration(exclude = {RedisAutoConfiguration.class, DataSourceAutoConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RedisTest {

    @Autowired
    CustomRedisTemplate customRedisTemplate;
    @Test
    public void testSet() {
        customRedisTemplate.set("testSet", "xxx");
        customRedisTemplate.lpush("list", 1);
        customRedisTemplate.lpush("list", 2);
        customRedisTemplate.lpush("list", 3);
        customRedisTemplate.lpush("list", 4);
        customRedisTemplate.lpush("list", 5);

    }

    @Test
    public void testGet() {
        System.out.println(customRedisTemplate.get("testSet"));
        System.out.println("---------------------------------------------------");
        List<String> list = customRedisTemplate.range("list", 0, -1, String.class);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("---------------------------------------------------");
        System.out.println(customRedisTemplate.rpop("list", String.class));
    }

    @Test
    public void testDelete() {
        customRedisTemplate.delete("list");

    }
}
