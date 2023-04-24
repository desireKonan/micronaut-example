package com.example.books;

import com.example.books.entity.Author;
import com.example.books.entity.Book;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.transaction.SynchronousTransactionManager;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Singleton
@Transactional
public class DataLoader {
    public static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @PersistenceContext
    private final EntityManager entityManager;

    private final SynchronousTransactionManager<Connection> transactionManager;

    public DataLoader(EntityManager entityManager, SynchronousTransactionManager<Connection> transactionManager) {
        this.entityManager = entityManager;
        this.transactionManager = transactionManager;
    }

    @EventListener
    protected void init(StartupEvent event) {
        this.addAuthors();
        logger.debug("Initilization achevée !");
    }


    private void addAuthors() {
        // if no data yet in the database
        long countAuthors = transactionManager.executeRead(status ->
                ((Number) entityManager.createQuery("select count(*) from Auteur").getSingleResult()).longValue()
        );

        if (countAuthors == 0) {
            logger.debug("Loading initial data to the database");


            transactionManager.executeWrite(status -> {
                List<Author> authors = List.of(
                        new Author(UUID.randomUUID(), "Charles", "Dickens"),
                        new Author(UUID.randomUUID(), "Guy", "Montpassant"),
                        new Author(UUID.randomUUID(), "Serges", "Bilé"),
                        new Author(UUID.randomUUID(), "Howard", "Gardner")
                );

                authors.forEach(entityManager::merge);
                return authors;
            });

            List<Author> authors = transactionManager.executeRead(status ->
                    entityManager.createQuery("from Auteur a").getResultList());

            //Add Book for 1 person.
            addBooks(authors.get(0));
            addBooks(authors.get(2));
        } else {
            List<Author> authors = transactionManager.executeRead(status ->
                    entityManager.createQuery("from Auteur a").getResultList());

            //Add Book for 1 person.
            addBooks(authors.get(0));
            addBooks(authors.get(2));
        }
    }


    private void addBooks(Author author) {
        // if no data yet in the database
        long countBooks = transactionManager.executeRead(status ->
                ((Number) entityManager.createQuery("select count(*) from Livre").getSingleResult()).longValue()
        );

        if (countBooks == 0) {
            logger.debug("Loading initial data to the database");

            List<Book> books = List.of(
                    new Book(UUID.randomUUID(), "Oliver Twist", "Story about Oliver Twist", author),
                    new Book(UUID.randomUUID(), "Greats Hopes", "Great story about hopes", author),
                    new Book(UUID.randomUUID(), "A tale for 2 cities", "Just read it", author),
                    new Book(UUID.randomUUID(), "Beak house", "Do you like bleak house", author)
            );

            transactionManager.executeWrite(status -> {
                books.forEach(entityManager::merge);
                return books;
            });
        }
    }
}
