package org.example.javalibraryservice.service;

import org.example.javalibraryservice.dto.JwtRequestDto;
import org.example.javalibraryservice.dto.RegistrationUserDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> createAuthToken(JwtRequestDto authRequest);
    ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto);
}
