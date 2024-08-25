package org.example.javalibraryservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.javalibraryservice.dto.JwtRequestDto;
import org.example.javalibraryservice.dto.JwtResponseDto;
import org.example.javalibraryservice.dto.RegistrationUserDto;
import org.example.javalibraryservice.dto.UserDto;
import org.example.javalibraryservice.exception.exceptions.ApiExceptionDto;
import org.example.javalibraryservice.service.AuthService;
import org.example.javalibraryservice.service.UserService;
import org.example.javalibraryservice.utils.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDto authRequest) {
        return authService.createAuthToken(authRequest);

    }

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}
