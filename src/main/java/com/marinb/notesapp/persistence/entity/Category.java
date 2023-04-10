package com.marinb.notesapp.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long id;
    @Column(nullable = false, length = 30)
    private String name;
    @Column(name = "category_note_id")
    @JsonIgnore
    @ManyToMany (mappedBy = "categories")
    private Set<Note> notes;

    public Category(Long id, String name, Set<Note> notes) {
        this.id = id;
        this.name = name;
        this.notes = notes;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
}
