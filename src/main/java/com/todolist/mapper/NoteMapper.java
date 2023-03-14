package com.todolist.mapper;


import com.todolist.Note;
import com.todolist.NoteEntity;
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
        Note inputNote = new Note();
        inputNote.setId(ouptut.getId());
        inputNote.setContent(ouptut.getContent());
        return inputNote;
    }
}
