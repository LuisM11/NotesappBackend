package com.marinb.notesapp.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_note")
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 1000)
    private String content;
    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean archived;
    @Column(name = "last_modified",nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime lastModified;

    @Column(name = "note_category_id")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "note_category",
            joinColumns = @JoinColumn(name = "id_note"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private Set<Category> categories;

    @PrePersist
    @PreUpdate
    public void initialDate(){
        this.lastModified = LocalDateTime.now();
    }

    public Note(Long id, String title, String content, boolean archived, Set<Category> categories) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.archived = archived;
        this.categories = categories;
    }

    public Note(String title, String content, boolean archived, Set<Category> categories) {
        this.title = title;
        this.content = content;
        this.archived = archived;
        this.categories = categories;
    }

    public Note() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
