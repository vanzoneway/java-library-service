package org.example.javalibraryservice.repositories;

import org.example.javalibraryservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);
    Boolean existsByIsbn(String isbn);

}
