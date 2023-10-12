package org.pancakeapple;

import org.junit.jupiter.api.Test;
import org.pancakeapple.properties.AliOSSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.sql.DataSource;

@SpringBootTest
class EmojiServerApplicationTests {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AliOSSProperties aliOSSProperties;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Test
    void contextLoads() {
        System.out.println(dataSource);
        System.out.println(aliOSSProperties.getBucketName());
    }

    @Test
    void testRedis() {
        redisTemplate.opsForValue().set("test","sky");
    }
}
