package com.week5.Homework.Sohel_Week5_Homework.controllers;

import com.week5.Homework.Sohel_Week5_Homework.dto.LoginDto;
import com.week5.Homework.Sohel_Week5_Homework.dto.SignUpDto;
import com.week5.Homework.Sohel_Week5_Homework.dto.UserDto;
import com.week5.Homework.Sohel_Week5_Homework.services.AuthService;
import com.week5.Homework.Sohel_Week5_Homework.services.Userservice;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;
    private final Userservice userservice;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup( @RequestBody SignUpDto signUpDto){
        UserDto user = userservice.signup(signUpDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletResponse response){
        String  token = authService.login(loginDto);

        Cookie cookie = new Cookie("token",token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
}
