package org.example.javalibraryservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;


@Getter
@Setter
public class UserDto {

    String username;

    String password;

    String email;
}