package com.example.todolist;

import java.util.List;

public interface TodoListService {
    Note saveNote(Note note);
    Note updateNote(Note note, Long id) throws Exception;
    void deleteNote(Long id);
    List<Note> getListNotes();

    Note getNote(Long id) throws Exception;
}
