package org.pancakeapple;

import org.junit.jupiter.api.Test;
import org.pancakeapple.properties.AliOSSProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        System.out.println(dataSource);
        System.out.println(aliOSSProperties.getBucketName());
    }

    @Test
    void testRedis() {
        redisTemplate.opsForValue().set("test","sky");
    }

    @Test
    void testRabbitMQ() {
        String queueName="test.queue";
        String message="Hello RabbitMQ";
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
