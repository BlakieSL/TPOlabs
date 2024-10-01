package com.example.lab10.Repository;

import com.example.lab10.Model.Url;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Integer> {
    Optional<Url> findUrlByUrlId(String urlId);
    void deleteUrlByUrlId(String urlId);
}
