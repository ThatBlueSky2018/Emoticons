package org.pancakeapple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
public class EmojiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmojiServerApplication.class, args);
    }

}
