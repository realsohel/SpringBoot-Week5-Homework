package com.week5.Homework.Sohel_Week5_Homework.filters;

import com.week5.Homework.Sohel_Week5_Homework.entities.UserEntity;
import com.week5.Homework.Sohel_Week5_Homework.services.JwtService;
import com.week5.Homework.Sohel_Week5_Homework.services.Userservice;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter  extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final Userservice userservice;

    @Qualifier("handlerExceptionResolver")
    private final HandlerExceptionResolver handlerExceptionResolver;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            String requiredToken = request.getHeader("Authorization");

            if (requiredToken == null || !requiredToken.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = requiredToken.substring(7);

            Long userId = jwtService.getUserById(token);

            if(userId!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserEntity user = userservice.getUserById(userId);

                UsernamePasswordAuthenticationToken authenticationToken=
                        new UsernamePasswordAuthenticationToken(user,null,null);

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            filterChain.doFilter(request,response);
        }catch(Exception e){
            handlerExceptionResolver.resolveException(request,response,null,e);
        }
    }
}
