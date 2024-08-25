package org.example.javalibraryservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.javalibraryservice.dto.BookDto;
import org.example.javalibraryservice.dto.Marker;
import org.example.javalibraryservice.service.impl.BookServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@AllArgsConstructor
@Validated
@Tag(name = "Book Controller", description = """
        This controller provides basic operations, such as:
            retrieving a list of all books;
            retrieving a specific book by its Id;
            retrieving a book by its ISBN;
            adding a new book;
            updating the information of an existing book;
            deleting a book.
        """)
public class BookController {

    BookServiceImpl bookService;

    @Operation(summary = "Retrieving a list of all books in the database")
    @SecurityRequirement(name = "JWT")
    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Operation(summary = "Retrieving a book by id from database")
    @SecurityRequirement(name = "JWT")
    @GetMapping("/book")
    public BookDto getBookById(@RequestParam("id") @Parameter(description = "The ID of the book") Long id) {
        return bookService.getBookById(id);
    }


    @Operation(summary = "Retrieving a book by isbn from database")
    @SecurityRequirement(name = "JWT")
    @GetMapping("/book/isbn")
    public BookDto getBookByIsbn(@RequestParam("isbn") @Parameter(description = "The ISBN of the book") String isbn) {
        return bookService.getBookByIsbn(isbn);
    }


    @Operation(summary = "Creates a book in database",
            description = "Returns a BookDto with ID null, but in database id has generation type GENERATED")
    @SecurityRequirement(name = "JWT")
    @PostMapping
    @Validated({Marker.OnCreate.class})
    public BookDto createBook(@RequestBody @Valid BookDto bookDto) {
        bookService.createBook(bookDto);
        return bookDto;
    }

    @Operation(summary = "Update one or more fields of book by id in database",
            description = "Returns a BookDto with fields, which are going to be changed. Other fields are marked as null")
    @SecurityRequirement(name = "JWT")
    @PutMapping("/book")
    public BookDto updateBook(@RequestParam Long id, @RequestBody BookDto bookDto) {
        bookService.updateBook(id, bookDto);
        return bookDto;
    }

    @Operation(summary = "Deletes book from database")
    @SecurityRequirement(name = "JWT")
    @DeleteMapping("/book")
    public void deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
    }


}
