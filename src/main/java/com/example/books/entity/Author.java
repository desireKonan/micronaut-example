package com.example.books.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "auteur")
public class Author extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "nom", nullable = false)
    private String firstName;

    @Column(name = "prenoms", nullable = false)
    private String lastName;
}
