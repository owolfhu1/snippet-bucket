package com.example.demo.repository;

import com.example.demo.model.Snippet;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface SnippetRepository extends CrudRepository<Snippet, Integer>{
    ArrayList<Snippet> findAllByTitleContaining(String title);
    ArrayList<Snippet> findAllByLanguagesContaining(String language);
}
