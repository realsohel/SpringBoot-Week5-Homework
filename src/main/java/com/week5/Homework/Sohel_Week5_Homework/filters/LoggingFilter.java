package com.week5.Homework.Sohel_Week5_Homework.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        RequestLogging(request);
        filterChain.doFilter(request, response);
        ResponseLogging(response);
    }

    private void RequestLogging(HttpServletRequest request){
        log.info("Incoming Request: {} {} from {} inside {}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr(),"LoggingFilter");
    }

    private void ResponseLogging(HttpServletResponse response){
        log.info("Outgoing Response: status={} inside {}", response.getStatus(),"LoggingFilter" );
    }
}
