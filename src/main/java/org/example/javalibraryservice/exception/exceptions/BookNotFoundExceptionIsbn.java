package org.example.javalibraryservice.exception.exceptions;

public class BookNotFoundExceptionIsbn extends RuntimeException {
    public BookNotFoundExceptionIsbn(String isbn) {
        super(String.format("Book with ISBN %s not found", isbn));
    }
}
