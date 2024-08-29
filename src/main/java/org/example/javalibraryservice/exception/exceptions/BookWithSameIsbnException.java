package org.example.javalibraryservice.exception.exceptions;

public class BookWithSameIsbnException extends RuntimeException {
    public BookWithSameIsbnException(String isbn) {
        super(String.format("Book with ISBN %s already exists", isbn));
    }
}
