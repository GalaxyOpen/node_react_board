package com.lkh.board_back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings (CorsRegistry corsRegistry){
        corsRegistry
        .addMapping("/**")
        .allowedMethods("GET","POST","PUT","DELETE","PATCH")
        .allowedOrigins("http://localhost:3000")
        .allowedHeaders("*")
        .allowCredentials(true);
    }
}
