package com.marinb.notesapp.persistence.repository;

import com.marinb.notesapp.persistence.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NoteRepository extends JpaRepository< Note,Long> {
     List<Note> findByCategoriesId(Long id);
     List<Note> findByArchived(boolean archived);

}
