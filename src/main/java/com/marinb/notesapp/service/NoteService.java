package com.marinb.notesapp.service;

import com.marinb.notesapp.dto.NoteDTO;
import com.marinb.notesapp.exception.BadRequestException;
import com.marinb.notesapp.exception.ResourceNotFoundException;
import com.marinb.notesapp.persistence.entity.Category;
import com.marinb.notesapp.persistence.entity.Note;
import com.marinb.notesapp.persistence.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    private NoteRepository noteRepository;
    private CategoryService categoryService;

    public NoteService(NoteRepository noteRepository, CategoryService categoryService) {
        this.noteRepository = noteRepository;
        this.categoryService = categoryService;
    }

    public Optional<Note> searchNote (Long id) {
        return noteRepository.findById(id);
    }

    public List<Note> listNotes(boolean archived) {
        return noteRepository.findByArchived(archived); //Search for notes by archived status
    }

    public Note getNote (Long id) throws ResourceNotFoundException {
        return searchNote(id).orElseThrow(() -> new ResourceNotFoundException("Note with id " + id + " does not exist"));
    }

    public Note createNote(NoteDTO noteDTO) throws BadRequestException {
        List<Category> categories = verifyCategories(noteDTO);
        return noteRepository.save(new Note(
                noteDTO.getTitle(),
                noteDTO.getContent(),
                false,
                new HashSet<>(categories)
        ));
    }

    public Note updateNote (Long id, NoteDTO noteDTO) throws BadRequestException,ResourceNotFoundException {
        Note note = searchNote(id).orElseThrow(() -> new ResourceNotFoundException("Note with id " + noteDTO.getId() + " does not exist"));
        List<Category> categories = verifyCategories(noteDTO);
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setCategories(new HashSet<>(categories));
        return noteRepository.save(note);
    }

    public Note toggleArchivedNote (Long id) throws ResourceNotFoundException {
        Note note = searchNote(id).orElseThrow(() -> new ResourceNotFoundException("Note with id " + id + " does not exist"));
        note.setArchived(!note.isArchived());
        return noteRepository.save(note);
    }
    public void deleteNote (Long id) throws ResourceNotFoundException {
        Note note = searchNote(id).orElseThrow(() -> new ResourceNotFoundException("Note with id " + id + " does not exist"));
        noteRepository.delete(note);
    }

//    UTILS --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private List<Category> verifyCategories (NoteDTO noteDTO) throws BadRequestException {
        if(noteDTO.getCategories() == null || noteDTO.getNewCategories() == null){
            throw new BadRequestException("Categories cannot be null, specify empty list if there are no categories");
        }
        if(noteDTO.getCategories().isEmpty() && noteDTO.getNewCategories().isEmpty()){
            throw new BadRequestException("Note must have at least one category");
        }

        List<Category> categories = noteDTO.getCategories().stream()
                .map(categoryId -> categoryService.searchCategory(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with id " + categoryId + " does not exist")))
                .toList();
        List<Category> newCategories = noteDTO.getNewCategories();
        List<Category> allCategories = new ArrayList<>();
        allCategories.addAll(categories);
        allCategories.addAll(newCategories);
        return allCategories;
    }

}
