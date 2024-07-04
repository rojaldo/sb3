package com.example.demo.library.books;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

    @Autowired
    BooksRespository booksRespository;

    public Optional<BookDto> createBook(BookDto book) {
        // search isbn book in the database
        Optional<BookEntity> be = this.booksRespository.findByIsbn(book.getIsbn());
        if (be.isPresent()) {
            return Optional.empty();
        }
        BookEntity bookEntity = BookEntity.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .build();
        this.booksRespository.save(bookEntity);
        return Optional.of(BookDto.builder()
                .isbn(bookEntity.getIsbn())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .description(bookEntity.getDescription())
                .build());
    }

    public BookDto getBookById(long id) {
        BookEntity be = this.booksRespository.findById(id);
        if (be == null) {
            return null;
        }
        return BookDto.builder()
                .isbn(be.getIsbn())
                .title(be.getTitle())
                .author(be.getAuthor())
                .description(be.getDescription())
                .build();
    }

    public BookEntity getBookEntityById(long id) {
        return this.booksRespository.findById(id);
    }

    public Iterable<BookDto> getAllBooks() {
        Iterable<BookEntity> bookEntities = this.booksRespository.findAll();
        return this.convertBookEntitiesToBookDtos(bookEntities);
    }

    public BookDto deleteBookById (long id) {
        BookEntity be = this.booksRespository.findById(id);
        if (be == null) {
            return null;
        }else {
            this.booksRespository.deleteById(id);
            return BookDto.builder()
                    .isbn(be.getIsbn())
                    .title(be.getTitle())
                    .author(be.getAuthor())
                    .description(be.getDescription())
                    .build();
        }
    }

    public Optional<BookDto> modifyBook (BookDto book, long id){
        BookEntity be = this.booksRespository.findById(id);
        if (be == null) {
            return Optional.empty();
        }else {
            be.setIsbn(book.getIsbn());
            be.setTitle(book.getTitle());
            be.setAuthor(book.getAuthor());
            be.setDescription(book.getDescription());
            this.booksRespository.save(be);
            return Optional.of(BookDto.builder()
                    .isbn(be.getIsbn())
                    .title(be.getTitle())
                    .author(be.getAuthor())
                    .description(be.getDescription())
                    .build());
        }
    }

    public Optional<BookDto> updateBook (BookDto book, long id){
        BookEntity be = this.booksRespository.findById(id);
        if (be == null) {
            return Optional.empty();
        }else {
            if (book.getIsbn() != null) {
                return Optional.empty();
            }
            if (book.getTitle() != null) {
                be.setTitle(book.getTitle());
            }
            if (book.getAuthor() != null) {
                be.setAuthor(book.getAuthor());
            }
            if (book.getDescription() != null) {
                be.setDescription(book.getDescription());
            }
            this.booksRespository.save(be);
            return Optional.of(BookDto.builder()
                    .isbn(be.getIsbn())
                    .title(be.getTitle())
                    .author(be.getAuthor())
                    .description(be.getDescription())
                    .build());
        }
    }

    private Iterable<BookDto> convertBookEntitiesToBookDtos(Iterable<BookEntity> bookEntities) {
        return StreamSupport.stream(bookEntities.spliterator(), false)
                .map(be -> BookDto.builder()
                        .isbn(be.getIsbn())
                        .title(be.getTitle())
                        .author(be.getAuthor())
                        .description(be.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
    
}
