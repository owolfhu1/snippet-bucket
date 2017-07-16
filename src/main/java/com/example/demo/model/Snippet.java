package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Snippet {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(columnDefinition="blob")
    private String code;
    private String title;
    private String languages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}
