package pl.edu.pja.s28250.lab04;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int year_public;
    @ManyToOne
    private Publisher publisher;
    @ManyToMany
    private final List<Author> authors = new ArrayList<>();
    public Book(String title, int year_public, Publisher publisher) {
        this.title = title;
        this.year_public = year_public;
        this.publisher = publisher;
    }

    public Book() {

    }
}
