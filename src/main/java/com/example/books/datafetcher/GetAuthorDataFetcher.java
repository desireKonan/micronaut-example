package com.example.books.datafetcher;

import com.example.books.entity.Author;
import com.example.books.repository.AuthorRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import jakarta.inject.Singleton;
import java.util.List;

@Singleton
public class GetAuthorDataFetcher implements DataFetcher<List<Author>> {

    private final AuthorRepository authorRepository;

    public GetAuthorDataFetcher(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        return this.authorRepository.findAll();
    }
}
