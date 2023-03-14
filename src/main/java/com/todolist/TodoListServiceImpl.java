package com.todolist;


import com.todolist.mapper.NoteMapper;
import jakarta.inject.Singleton;
import java.util.Date;
import java.util.List;

@Singleton
public class TodoListServiceImpl implements TodoListService {

    private final NoteRepository repository;

    private final NoteMapper noteMapper;

    public TodoListServiceImpl(NoteRepository repository, NoteMapper noteMapper) {
        this.repository = repository;
        this.noteMapper = noteMapper;
    }

    @Override
    public Note saveNote(Note note) {
        NoteEntity noteEntity = new NoteEntity();
        noteMapper.mapTo(note, noteEntity);
        return noteMapper.transformInto(repository.save(noteEntity));
    }

    @Override
    public Note updateNote(Note note, Long id) throws Exception {
        NoteEntity noteEntityFound = repository
                .findById(id)
                .orElseThrow(() -> new Exception("L'élément n'existe pas !"));
        noteMapper.mapTo(note, noteEntityFound);
        noteEntityFound.setUpdatedAt(new Date());
        NoteEntity noteEntitySaved = repository.update(noteEntityFound);
        return noteMapper.transformInto(noteEntitySaved);
    }

    @Override
    public void deleteNote(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Note> getListNotes() {
        List<NoteEntity> noteEntityList = this.repository.findAll();
        List<Note> noteList = noteEntityList.stream().map(noteMapper::transformInto).toList();
        return noteList;
    }

    @Override
    public Note getNote(Long id) throws Exception {
        NoteEntity noteEntityFound = this.repository.findById(id).orElseThrow(() -> new Exception("L'élément n'existe pas !"));
        return noteMapper.transformInto(noteEntityFound);
    }
}
