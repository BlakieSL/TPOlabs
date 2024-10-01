package com.example.lab04blog;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy =  "roles")
    private Set<User> users = new HashSet<>();
    public Role(){}
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name =  name;
    }
    public Set<User> getUsers(){
        return users;
    }
    public void setUsers(Set<User> users){
        this.users = users;
    }

    public Long getId() {
        return id;
    }
}
