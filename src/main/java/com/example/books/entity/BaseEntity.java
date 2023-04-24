package com.example.books.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.Date;


@MappedSuperclass
public abstract class BaseEntity {
    @Column(name = "creationDate", nullable = true)
    private Date createdAt;

    @Column(name = "editDate", nullable = true)
    private Date updatedAt;

    @PrePersist
    public void initCreatedDate() {
        this.setCreatedAt(new Date());
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
