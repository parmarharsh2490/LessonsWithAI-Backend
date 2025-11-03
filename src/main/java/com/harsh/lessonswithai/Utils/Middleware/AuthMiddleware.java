package com.harsh.lessonswithai.Utils.Middleware;

import com.harsh.lessonswithai.Utils.Auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class AuthMiddleware implements HandlerInterceptor {

    private final AuthService authService;
    private final JwtDecoder jwtDecoder;

    public AuthMiddleware(AuthService authService, JwtDecoder jwtDecoder) {
        this.authService = authService;
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                Jwt jwt = jwtDecoder.decode(token);
                String userId = jwt.getClaimAsString("sub");
                String email = jwt.getClaimAsString("email");

                log.info("User ID: {}", userId);
                log.info("Email: {}", email);

                authService.setUser_id(userId);
                authService.setEmail(email);

            } catch (Exception e) {
                log.error("JWT verification failed: {}", e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        } else {
            log.warn("Missing or invalid Authorization header");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return true;
        }

        return true;
    }
}
