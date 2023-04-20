package com.example.books.service.impl;

import com.example.books.entity.Author;
import com.example.books.repository.AuthorRepository;
import com.example.books.service.AuthorService;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Author> getAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> getById(UUID id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public void create(Author author) {
        this.authorRepository.save(author);
    }
}
