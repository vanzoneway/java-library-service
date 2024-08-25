package org.example.javalibraryservice.exception.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super(String.format("Book with ID %d not found", id));
    }
}
