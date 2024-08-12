package com.week5.Homework.Sohel_Week5_Homework.services;

import com.week5.Homework.Sohel_Week5_Homework.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
@Transactional
public class JwtService  {
    @Value("${jwt.secretKey}")
    private String jwtSecret;

    private SecretKey createKey(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserEntity user){
        return Jwts
                .builder()
                .subject(user.getId().toString())
                .claim("email",user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60))
                .signWith(createKey())
                .compact();
    }

    public Long getUserById(String token){
        Claims claims = Jwts
                .parser()
                .verifyWith(createKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.valueOf(claims.getSubject());


    }
}
