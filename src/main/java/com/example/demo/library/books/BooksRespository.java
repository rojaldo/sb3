package com.example.demo.library.books;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRespository extends JpaRepository<BookEntity, Long> {

    BookEntity findById(long id);
    Optional<BookEntity> findByIsbn(String isbn);
    BookEntity findByTitle(String title);
    BookEntity findByAuthor(String author);
    BookEntity findByAuthorAndTitle(String author, String title);

}
