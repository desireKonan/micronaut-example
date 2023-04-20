package com.example.books.repository;

import com.example.books.entity.Book;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
}
