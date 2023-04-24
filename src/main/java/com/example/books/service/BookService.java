package com.example.books.service;

import com.example.books.entity.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    List<Book> getAllBooks();

    Optional<Book> getByBookId(UUID id);

    Book createBook(Book book);
}
