package com.harsh.lessonswithai.Utils.CORS;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {
    @Value("${spring.frontend.url}")
    String FRONTEND_URL;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        System.out.println("FRONTENDURL : {}" + FRONTEND_URL);
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(List.of(FRONTEND_URL));
        cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        cors.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With", "Origin", "Accept"));
        cors.setExposedHeaders(List.of("Content-Disposition"));
        cors.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);
        return source;
    }
}