package com.myapp.webprj.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    //localhost로 들어갔을 때 로그인 창 안뜨게 하는 옵션
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
    }
}
