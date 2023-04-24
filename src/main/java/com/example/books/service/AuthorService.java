package com.example.books.service;

import com.example.books.entity.Author;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorService {

    List<Author> getAllAuthors();

    Optional<Author> getByAuthorId(UUID id);

    Author createAuthor(Author author);
}
