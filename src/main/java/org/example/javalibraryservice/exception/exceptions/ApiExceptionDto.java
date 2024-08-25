package org.example.javalibraryservice.exception.exceptions;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class ApiExceptionDto{

    @Schema(description = "Http response status", example = "400")
    HttpStatus status;

    @Schema(description = "Error message", example = "Bad request")
    String message;

    @Schema(description = "Timestamp of the error", example = "2021-08-01T12:00:00")
    LocalDateTime timestamp;

}