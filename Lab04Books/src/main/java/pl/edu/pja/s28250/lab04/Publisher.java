package pl.edu.pja.s28250.lab04;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "publisher", fetch = FetchType.EAGER,
    cascade = CascadeType.PERSIST)
    private final List<Book> books = new ArrayList<>();
    private int year_foundation;
    public Publisher(String name, int year_foundation) {
        this.name = name;
        this.year_foundation = year_foundation;
    }

    public Publisher() {

    }
}
