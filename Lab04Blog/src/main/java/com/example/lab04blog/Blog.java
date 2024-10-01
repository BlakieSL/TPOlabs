package com.example.lab04blog;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "blog",cascade = CascadeType.ALL,fetch = FetchType.EAGER )
    private Set<Article> articles = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "manager_id")
    private User manager;
    public Blog(){}

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Set<Article> getArticles(){
        return articles;
    }
    public void setArticles(Set<Article> articles){
        this.articles = articles;
    }
    public User getManager(){
        return manager;
    }
    public void setManager(User manager){
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }
}
