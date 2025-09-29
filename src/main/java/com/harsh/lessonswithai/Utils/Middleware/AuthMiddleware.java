package com.harsh.lessonswithai.Utils.Middleware;

import com.harsh.lessonswithai.Utils.Auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Slf4j
@Component
public class AuthMiddleware implements HandlerInterceptor {
    private final AuthService authService;

    public AuthMiddleware(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    )
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
            Map<String, Object> attributes = oauthToken.getPrincipal().getAttributes();
            String userId = (String) attributes.get("sub");   // Keycloak unique ID
            String email  = (String) attributes.get("email"); // user email
            authService.setEmail(email);
            authService.setUser_id(userId);
        }
        return true;
    }
}
