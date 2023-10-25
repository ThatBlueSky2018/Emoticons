package org.pancakeapple.config;

import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.RBACConstant;
import org.pancakeapple.filter.JwtAuthenticationFilter;
import org.pancakeapple.security.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

/**
 * Spring Security核心配置类
 * Author SKY 2023/9/19
 */
@Configuration
@Slf4j
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter ;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService ;

    //CORS跨域请求配置
    @Bean
    public CorsConfigurationSource corsConfigSource() {
        log.info("配置CORS跨域请求...");
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception
    {
        log.info("配置Spring Security过滤器链...");
        http
            .cors(cors->cors.configurationSource(corsConfigSource()))
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth ->{
                //注意：配置在前面的先起到效果！
                auth.requestMatchers("/user/login").permitAll();
                auth.requestMatchers("/user/register").permitAll();
                auth.requestMatchers("/user/user").hasAuthority(RBACConstant.USER_ROLE);
                auth.requestMatchers("/user/admin").hasAuthority(RBACConstant.ADMIN_ROLE);
                auth.requestMatchers("/user/**").hasAuthority(RBACConstant.USER_ROLE);

                auth.requestMatchers("/tagGroup/list").hasAuthority(RBACConstant.USER_ROLE);
                auth.requestMatchers("/tagGroup", "/tagGroup/**").hasAuthority(RBACConstant.ADMIN_ROLE);

                auth.requestMatchers("/tag").hasAuthority(RBACConstant.USER_ROLE);
                auth.requestMatchers("/tag/**").hasAuthority(RBACConstant.ADMIN_ROLE);

                auth.requestMatchers("/emoji","/emoji/**").hasAuthority(RBACConstant.USER_ROLE);
                auth.requestMatchers("/common/**").hasAuthority(RBACConstant.USER_ROLE);

                auth.requestMatchers("/favorite","/favorite/**").hasAuthority(RBACConstant.USER_ROLE);

                auth.requestMatchers("/doc.html","/webjars/**","/swagger-ui.html","/img.icons/**",
                        "/swagger-resources/**","/**","/v3/api-docs").permitAll();
            })
            .httpBasic(Customizer.withDefaults());
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return  http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        log.info("配置认证管理器...");
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("配置密码编码器...");
        return new BCryptPasswordEncoder();
    }

}