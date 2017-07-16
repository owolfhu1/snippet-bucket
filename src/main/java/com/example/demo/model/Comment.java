package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private int snippetId;
    @Column(columnDefinition="blob")
    private String body;
    private String name;

    public int getSnippetId() {
        return snippetId;
    }

    public void setSnippetId(int snippetId) {
        this.snippetId = snippetId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
