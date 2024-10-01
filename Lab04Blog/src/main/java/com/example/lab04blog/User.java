package com.example.lab04blog;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.EAGER )
    private Set<Article> articles = new HashSet<>();
    @ManyToMany
    private Set<Role> roles = new HashSet<>();
    @OneToOne(mappedBy = "manager")
    private Blog managedBlog;
    public User(){}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public Set<Article> getArticles() {
        return articles;
    }
    public void setArticles(Set<Article> articles){
        this.articles = articles;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }

    public Blog getManagedBlog() {
        return managedBlog;
    }
    public void setManagedBlog(Blog managedBlog){
        this.managedBlog = managedBlog;
    }

    public Long getId() {
        return id;
    }
}
