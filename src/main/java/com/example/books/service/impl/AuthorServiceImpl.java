package com.example.books.service.impl;

import com.example.GraphQLService;
import com.example.books.entity.Author;
import com.example.books.repository.AuthorRepository;
import com.example.books.service.AuthorService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@GraphQLService
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @GraphQLQuery
    @Override
    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    @GraphQLQuery
    @Override
    public Optional<Author> getByAuthorId(@GraphQLArgument(name = "id") UUID id) {
        return this.authorRepository.findById(id);
    }

    @GraphQLMutation
    @Override
    public Author createAuthor(@GraphQLArgument(name = "author") Author author) {
        Author authorSaved = this.authorRepository.save(author);
        return authorSaved;
    }
}
