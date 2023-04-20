package com.example.books.datafetcher;

import com.example.books.entity.Book;
import com.example.books.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class GetBooksDataFetcher implements DataFetcher<List<Book>> {

    private final BookRepository bookRepository;

    public GetBooksDataFetcher(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        return this.bookRepository.findAll();
    }
}
