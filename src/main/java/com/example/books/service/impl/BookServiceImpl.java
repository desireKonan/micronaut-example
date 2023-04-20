package com.example.books.service.impl;

import com.example.books.entity.Book;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> getById(UUID id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public void create(Book book) {
        this.bookRepository.save(book);
    }
}
