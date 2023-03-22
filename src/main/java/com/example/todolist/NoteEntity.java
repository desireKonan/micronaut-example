package com.example.todolist;


import io.micronaut.data.annotation.MappedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
//@Entity
@Table(name = "note")
@MappedEntity
public class NoteEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private Date createdAt;

    private Date updatedAt;
}
