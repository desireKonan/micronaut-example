package com.example.todolist.mapper;


import com.example.todolist.Note;
import com.example.todolist.NoteEntity;
import jakarta.inject.Singleton;
import java.util.Date;

@Singleton
public class NoteMapper implements Mapper<Note, NoteEntity> {
    @Override
    public void mapTo(Note input, NoteEntity output) {
        output.setContent(input.getContent());
        output.setCreatedAt(new Date());
    }

    @Override
    public Note transformInto(NoteEntity ouptut) {
        return new Note(ouptut.getId(), ouptut.getContent());
    }
}
