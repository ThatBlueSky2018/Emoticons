package org.pancakeapple;

import org.junit.jupiter.api.Test;
import org.pancakeapple.properties.AliOSSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class EmojiServerApplicationTests {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AliOSSProperties aliOSSProperties;

    @Test
    void contextLoads() {
        System.out.println(dataSource);
        System.out.println(aliOSSProperties.getBucketName());
    }
}
