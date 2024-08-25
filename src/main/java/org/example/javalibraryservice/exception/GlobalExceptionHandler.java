package org.example.javalibraryservice.exception;

import jakarta.validation.ConstraintViolationException;
import org.example.javalibraryservice.exception.exceptions.*;
import org.example.javalibraryservice.exception.violation.ValidationErrorResponse;
import org.example.javalibraryservice.exception.violation.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiExceptionDto handleBookException(BookNotFoundException e) {
        return new ApiExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(BookWithSameIsbnException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ApiExceptionDto handleBookWithSameIsbnException(BookWithSameIsbnException e) {
        return new ApiExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(BookNotFoundExceptionIsbn.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiExceptionDto handleBookNotFoundExceptionIsbn(BookNotFoundExceptionIsbn e) {
        return new ApiExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage(), LocalDateTime.now());
    }


    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(
            ConstraintViolationException e
    ) {
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(
                        violation -> new Violation(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiExceptionDto handleAuthException(AuthException e) {
        return new ApiExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ApiExceptionDto handleUnauthorizedException(BadCredentialsException e) {
        return new ApiExceptionDto(HttpStatus.UNAUTHORIZED, e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiExceptionDto handleInternalServerException(Exception e) {
        return new ApiExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), LocalDateTime.now());
    }

}
