package org.pancakeapple.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/***
 * 创建Swagger配置
 * @author SKY
 * 2023/09/27 21:30:00
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EmoVerse接口文档")
                        .version("1.0")
                        .description( "用于快速实现前后端联调")
                        .termsOfService("https://doc.xiaominfo.com")
                        .license(new License().name("Apache 2.0")
                                .url("https://doc.xiaominfo.com")));
    }


}