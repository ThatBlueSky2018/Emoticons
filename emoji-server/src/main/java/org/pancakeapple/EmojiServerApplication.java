package org.pancakeapple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EmojiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmojiServerApplication.class, args);
    }

}
