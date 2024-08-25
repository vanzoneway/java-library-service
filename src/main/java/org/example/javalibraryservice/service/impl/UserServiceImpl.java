package org.example.javalibraryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.javalibraryservice.dto.JwtRequestDto;
import org.example.javalibraryservice.dto.JwtResponseDto;
import org.example.javalibraryservice.dto.RegistrationUserDto;

import org.example.javalibraryservice.exception.exceptions.ApiExceptionDto;
import org.example.javalibraryservice.exception.exceptions.AuthException;

import org.example.javalibraryservice.model.User;

import org.example.javalibraryservice.repositories.RoleRepository;
import org.example.javalibraryservice.repositories.UserRepository;
import org.example.javalibraryservice.service.UserService;
import org.example.javalibraryservice.utils.JwtTokenUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Username %s not found", username)
        ));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList()
        );
    }

}
