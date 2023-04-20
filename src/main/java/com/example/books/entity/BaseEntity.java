package com.example.books.entity;

import javax.persistence.Column;
import java.util.Date;


public abstract class BaseEntity {
    @Column(name = "dateCreation", nullable = false)
    protected Date createdAt;

    @Column(name = "dateAJour", nullable = false)
    protected Date updatedAt;
}
