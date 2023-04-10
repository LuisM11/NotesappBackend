package com.marinb.notesapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class testDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;

    public testDTO(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "testDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
