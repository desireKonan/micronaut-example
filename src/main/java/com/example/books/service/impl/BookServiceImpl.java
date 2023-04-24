package com.example.books.service.impl;

import com.example.GraphQLService;
import com.example.books.entity.Book;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@GraphQLService
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GraphQLQuery
    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @GraphQLQuery
    @Override
    public Optional<Book> getByBookId(@GraphQLArgument(name = "id") UUID id) {
        return this.bookRepository.findById(id);
    }

    @GraphQLMutation
    @Override
    public Book createBook(@GraphQLArgument(name = "book") Book book) {
        return this.bookRepository.save(book);
    }
}
