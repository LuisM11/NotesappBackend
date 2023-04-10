package com.marinb.notesapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marinb.notesapp.persistence.entity.Category;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteDTO {
    private Long id;
    @JsonProperty("title")
    private String title;
    private String content;
    private boolean archived;
    private List<Long> categories;
    private List<Category> newCategories;

    public NoteDTO() {

    }

    public NoteDTO(Long id, String title, String content, boolean archived, List<Long> categories, List<Category> newCategories) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.archived = archived;
        this.categories = categories;
        this.newCategories = newCategories;
    }


    public NoteDTO(String title, String content, boolean archived, List<Long> categories, List<Category> newCategories) {

        this.title = title;
        this.content = content;
        this.archived = archived;
        this.categories = categories;
        this.newCategories = newCategories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }

    public List<Category> getNewCategories() {
        return newCategories;
    }

    public void setNewCategories(List<Category> newCategories) {
        this.newCategories = newCategories;
    }

    @Override
    public String toString() {
        return "NoteDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
