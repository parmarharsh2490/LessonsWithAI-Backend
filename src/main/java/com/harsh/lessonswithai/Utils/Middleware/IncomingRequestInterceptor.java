package com.harsh.lessonswithai.Utils.Middleware;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;

@Slf4j
@Component
public class IncomingRequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object object
    ) {
        HashMap<String,String> frontEndData = new HashMap<>();
        frontEndData.put("FRONTEND_URL",request.getRequestURL().toString());
        frontEndData.put("METHOD",request.getMethod());
        log.info("Incoming Request" + frontEndData);
        return true;
    }
}
