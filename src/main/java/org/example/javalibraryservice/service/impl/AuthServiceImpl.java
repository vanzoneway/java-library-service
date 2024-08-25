package org.example.javalibraryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.javalibraryservice.dto.JwtRequestDto;
import org.example.javalibraryservice.dto.JwtResponseDto;
import org.example.javalibraryservice.dto.RegistrationUserDto;
import org.example.javalibraryservice.exception.exceptions.AuthException;
import org.example.javalibraryservice.model.User;
import org.example.javalibraryservice.repositories.UserRepository;
import org.example.javalibraryservice.service.AuthService;
import org.example.javalibraryservice.service.UserService;
import org.example.javalibraryservice.utils.JwtTokenUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserService userService;


    @Override
    public ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            throw new AuthException("Passwords do not match");
        }

        if(userRepository.findByUsername(registrationUserDto.getUsername()).isPresent())
            throw new AuthException("Username is already in use");

        User user = modelMapper.map(registrationUserDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Override
    public ResponseEntity<?> createAuthToken(JwtRequestDto authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDto(token));
    }

}
