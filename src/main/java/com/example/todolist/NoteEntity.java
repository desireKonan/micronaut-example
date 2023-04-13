package com.example.todolist;

import io.micronaut.core.annotation.Introspected;
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
@Introspected
@NoArgsConstructor
@Table(name = "note")
@Entity
public class NoteEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private Date createdAt;

    private Date updatedAt;
}
