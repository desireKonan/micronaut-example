package com.example.books.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "livre")
public class Book extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "nom", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "auteur_id")
    private Author author;
}
