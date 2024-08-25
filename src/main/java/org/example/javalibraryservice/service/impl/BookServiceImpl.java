package org.example.javalibraryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.javalibraryservice.dto.BookDto;
import org.example.javalibraryservice.exception.exceptions.BookNotFoundException;
import org.example.javalibraryservice.exception.exceptions.BookNotFoundExceptionIsbn;
import org.example.javalibraryservice.exception.exceptions.BookWithSameIsbnException;
import org.example.javalibraryservice.model.Book;
import org.example.javalibraryservice.repositories.BookRepository;
import org.example.javalibraryservice.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map((element) -> modelMapper.map(element, BookDto.class))
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        return modelMapper.map(bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id)),
                BookDto.class);
    }

    @Override
    public BookDto getBookByIsbn(String isbn) {
        return modelMapper.map(bookRepository.findByIsbn(isbn)
                                .orElseThrow(() -> new BookNotFoundExceptionIsbn(isbn)),
                BookDto.class);
    }

    @Override
    public void createBook(BookDto bookDto) {
        if (bookRepository.existsByIsbn(bookDto.getIsbn())) {
            throw new BookWithSameIsbnException(bookDto.getIsbn());
        }
        Book createdBook = modelMapper.map(bookDto, Book.class);
        bookRepository.save(createdBook);
    }


    @Override
    public void updateBook(Long id, BookDto bookDto) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        if(bookDto.getIsbn() != null)
            existingBook.setIsbn(bookDto.getIsbn());

        if (bookDto.getName() != null)
            existingBook.setName(bookDto.getName());

        if (bookDto.getGenre() != null)
            existingBook.setGenre(bookDto.getGenre());

        if (bookDto.getDescription() != null)
            existingBook.setDescription(bookDto.getDescription());

        if (bookDto.getAuthor() != null)
            existingBook.setAuthor(bookDto.getAuthor());

        bookRepository.save(existingBook);
    }


    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }
}
