package pl.edu.pja.s28250.lab04;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    @ManyToMany(mappedBy = "authors")
    private final List<Book> books = new ArrayList<>();
    public Author(String name, int age){
        this.name = name;
        this.age = age;
    }

    public Author() {

    }
}
