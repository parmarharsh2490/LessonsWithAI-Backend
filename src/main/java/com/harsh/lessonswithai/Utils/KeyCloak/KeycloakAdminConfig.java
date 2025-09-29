package com.harsh.lessonswithai.Utils.KeyCloak;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class KeycloakAdminConfig {
    @Value("${spring.application.keycloak_realm_name}")
    private String keycloak_realm_name;

    @Value("${spring.application.keycloak_client_id}")
    private String keycloak_client_id;

    @Value("${spring.application.keycloak_url}")
    private String keycloak_url;

    @Value("${spring.application.admin_email}")
    private String admin_email;

    @Value("${spring.application.admin_password}")
    private String admin_password;

    @Bean
    public Keycloak keycloakAdmin() {
        log.info("Keycloak Admin " + admin_email);
        return KeycloakBuilder.builder()
                .serverUrl(keycloak_url) // Keycloak URL
                .realm("master")        // master realm for admin login
                .clientId("admin-cli")  // admin client             // Client with admin rights
                .username(admin_email)                   // Admin username
                .password(admin_password)                   // Admin password
                .build();
    }
}
