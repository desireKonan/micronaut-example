package com.example.todolist;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Delete;
import java.util.List;

@Controller(value = "api/v1/notes")
public class TodoListController {
    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @Get
    public HttpResponse<List<Note>> getNotes() {
        return HttpResponse.ok(this.todoListService.getListNotes());
    }

    @Post
    public Note saveNote(@Body Note note) {
        return this.todoListService.saveNote(note);
    }

    @Put("{id}")
    public Note updateNode(@Body Note note, @PathVariable Long id) throws Exception {
        return this.todoListService.updateNote(note, id);
    }


    @Get("{id}")
    public HttpResponse<Note> getNote(@PathVariable Long id) {
        try {
            Note note = this.todoListService.getNote(id);
            return HttpResponse.ok(note);
        } catch (Exception ex) {
            return HttpResponse.notFound();
        }
    }

    @Delete("{id}")
    public void deleteNote(@PathVariable Long id) {
        this.todoListService.deleteNote(id);
    }



    @Get(uri = "hello", produces = {MediaType.TEXT_PLAIN})
    public String helloWorld() {
        return "Hello World";
    }

}
