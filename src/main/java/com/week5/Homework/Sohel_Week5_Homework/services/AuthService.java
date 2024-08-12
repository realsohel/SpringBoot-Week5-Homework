package com.week5.Homework.Sohel_Week5_Homework.services;


import com.week5.Homework.Sohel_Week5_Homework.dto.LoginDto;
import com.week5.Homework.Sohel_Week5_Homework.dto.UserDto;
import com.week5.Homework.Sohel_Week5_Homework.entities.UserEntity;
import com.week5.Homework.Sohel_Week5_Homework.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final SessionService sessionService;

    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        );

        if( authentication.isAuthenticated()){
            UserEntity user = (UserEntity) authentication.getPrincipal();
            String token =  jwtService.generateToken(user);
            sessionService.generateNewSession(user,token);

            return token;
        }

        return "Not logged in!!";
    }
}
