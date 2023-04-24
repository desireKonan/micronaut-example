package com.example.books.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.util.UUID;


@Entity(name = "Auteur")
@Table(name = "auteur")
@AllArgsConstructor
@NoArgsConstructor
public class Author extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "nom", nullable = false)
    private String firstName;

    @Column(name = "prenoms", nullable = false)
    private String lastName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
