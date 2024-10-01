package com.example.lab04blog;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    private User author;
    @ManyToOne
    private Blog blog;
    public Article(){}
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public User getAuthor(){
        return author;
    }
    public void setAuthor(User author){
        this.author = author;
    }
    public Blog getBlog(){
        return blog;
    }
    public void setBlog(Blog blog){
        this.blog = blog;
    }

    public Long getId() {
        return id;
    }
}
