package com.example.demo.controller;


import com.example.demo.model.Comment;
import com.example.demo.model.Snippet;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class SnippitController {

    @Autowired
    SnippetRepository snippetRepository;
    @Autowired
    CommentRepository commentRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("snippet", new Snippet());
        return "index";
    }

    @RequestMapping("/save")
    public String save(Snippet snippet, Model model) {
        snippetRepository.save(snippet);
        model.addAttribute("snippet", new Snippet());
        return "index";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("snippet", new Snippet());
        return "input";
    }

    @RequestMapping("/all")
    public String all(Model model) {
        ArrayList<Snippet> list = new ArrayList<>();
        for (Snippet snip : snippetRepository.findAll()){
            snip.setCode("");
            list.add(snip);
        }
        model.addAttribute("list", list);
        return "results";
    }

    @RequestMapping("/search")
    public String search(Snippet snippet, Model model) {
        ArrayList<Snippet> snippets = new ArrayList<>();

        if (snippet.getTitle().length() > 0 && snippet.getLanguages().length() == 0)
            snippets = snippetRepository.findAllByTitleContaining(snippet.getTitle());


        else if (snippet.getLanguages().length() > 0 && snippet.getTitle().length() == 0)
            snippets = snippetRepository.findAllByLanguagesContaining(snippet.getLanguages());


        else if (snippet.getTitle().length() > 0 && snippet.getLanguages().length() > 0) {
            snippets = snippetRepository.findAllByTitleContaining(snippet.getTitle());
            for (int i = snippets.size() - 1; i > -1; i--) {
                snippets.get(i).setCode("");
                if (!snippets.get(i).getLanguages().contains(snippet.getLanguages()))
                    snippets.remove(i);
            }
        }
        model.addAttribute("list", snippets);
        return "results";
    }

    @RequestMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") int id) {
        model.addAttribute("snip", snippetRepository.findOne(id));
        model.addAttribute("comments", commentRepository.findAllBySnippetId(id));
        model.addAttribute("comment", new Comment());
        return "viewer";
    }

    @RequestMapping("/comment")
    public String comment(Model model, Comment comment) {
        commentRepository.save(comment);
        return "redirect:/view/" + comment.getSnippetId();
    }

    @RequestMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("snip", new Snippet());
        return "delete";
    }

    @RequestMapping("/deletethis")
    public String deleteThis(Snippet snippet) {
        //personally hashed pass
        if (snippet.getLanguages().hashCode() == 1635423)
            if (snippetRepository.exists(snippet.getId()))
                snippetRepository.delete(snippet.getId());
        return "redirect:/";
    }

}
