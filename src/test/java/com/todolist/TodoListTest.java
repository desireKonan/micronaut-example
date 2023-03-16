package com.todolist;

import com.example.todolist.Note;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import jakarta.inject.Inject;
import java.util.List;

@MicronautTest
class TodoListTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    @Client("/api/v1/")
    HttpClient httpClient;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }


    @Test
    void testListIsEmpty() {
        List<Note> notes = httpClient.toBlocking().retrieve(HttpRequest.GET("notes"), List.class);
        Assertions.assertNotEquals(0, notes.size());
    }


    @Test
    void getUrlNotesNot404() {
        int statusCode = httpClient.toBlocking().exchange("notes").status().getCode();
        Assertions.assertNotEquals(404, statusCode);
    }

    @Test
    void getUrlNotesNot200() {
        int statusCode = httpClient.toBlocking().exchange("notes").status().getCode();
        Assertions.assertEquals(200, statusCode);
    }


    @Test
    void testAddNewNote() {
        Note note = new Note();
        note.setContent("Notes supplémentaires !");
        Note noteSaved = httpClient.toBlocking().retrieve(HttpRequest.POST("note", note), Note.class);
        Assertions.assertEquals(note.getContent(), noteSaved.getContent());
    }


    @Test
    void testUpdateValidNote() throws Exception {
        Note note = new Note();
        note.setId(1L);
        note.setContent("New content part 4");
        Note noteSaved = httpClient.toBlocking().retrieve(HttpRequest.PUT("note/" + note.getId(), note), Note.class);
        Assertions.assertEquals(note.getContent(), noteSaved.getContent());
    }


    @Test
    void deleteNoteFromId() {
        HttpResponse<Note> httpResponse = httpClient.toBlocking().exchange(HttpRequest.DELETE("note/" + 1L, Note.class));
        Assertions.assertEquals(200, httpResponse.code());
        Assertions.assertEquals(null, httpResponse.body());
    }


    @Test
    void getNoteFromId() {
        HttpResponse<Note> httpResponse = httpClient.toBlocking().exchange(HttpRequest.GET("note/" + 2L), Note.class);
        Assertions.assertEquals((httpResponse.body() != null), true);
    }


    @Test
    void getNoteFromInexistantId() {
        HttpClientResponseException ex = Assertions.assertThrows(HttpClientResponseException.class, () ->
            httpClient.toBlocking().exchange(HttpRequest.GET("note/" + 3L), Note.class)
        );

        Assertions.assertEquals(404, ex.getStatus().getCode());
    }

}
