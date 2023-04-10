package com.marinb.notesapp.controller;

import com.marinb.notesapp.dto.NoteDTO;
import com.marinb.notesapp.exception.BadRequestException;
import com.marinb.notesapp.exception.ResourceNotFoundException;
import com.marinb.notesapp.persistence.entity.Note;
import com.marinb.notesapp.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping()
    public ResponseEntity<List<Note>> getNotes(@RequestParam(value = "archived" ,required = false) boolean archived) {
        List<Note> notes = noteService.listNotes(archived);
        if (notes.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(noteService.getNote(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Note> postNote(@RequestBody NoteDTO noteDTO) throws BadRequestException, ResourceNotFoundException {
        return new ResponseEntity<>(noteService.createNote(noteDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> putNote(@PathVariable("id") Long id, @RequestBody NoteDTO noteDTO) throws BadRequestException, ResourceNotFoundException {
        return new ResponseEntity<>(noteService.updateNote(id,noteDTO),HttpStatus.OK);
    }

    @PatchMapping("/{id}/archived")
    public ResponseEntity<Note> patchNote (@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(noteService.toggleArchivedNote(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNote (@PathVariable("id") Long id) throws ResourceNotFoundException {
        noteService.deleteNote(id);
        return new ResponseEntity<>("Note #" + id  + " deleted successfully",HttpStatus.NO_CONTENT);
    }



}
