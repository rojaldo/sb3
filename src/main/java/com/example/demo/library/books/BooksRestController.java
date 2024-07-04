package com.example.demo.library.books;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/library")
public class BooksRestController {

    @Autowired
    BooksService booksService;

    @GetMapping("/books")
    public ResponseEntity<Iterable<BookDto>> getBooks(BookDto author) {
        return ResponseEntity.ok().body(this.booksService.getAllBooks());
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> createBook(@RequestBody @Validated BookDto book) {
        Optional<BookDto> result = this.booksService.createBook(book);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result.get());
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDto> modifyBook(@RequestBody @Validated BookDto book, @PathVariable long id) {
        Optional<BookDto> result = this.booksService.modifyBook(book, id);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }

    @PatchMapping("/books/{id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody @Validated BookDto book, @PathVariable long id) {
        Optional<BookDto> result = this.booksService.updateBook(book, id);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable long id) {
        BookDto book = this.booksService.deleteBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(200).body(book);
        }
    }
    
}
