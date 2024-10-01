package com.example.lab10.Repository;

import com.example.lab10.Model.Url;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UrlRepository extends CrudRepository<Url, Integer> {
    Optional<Url> findUrlByUrlId(String urlId);
    void deleteUrlByUrlId(String urlId);
    Optional<Url> findUrlByTargetUrl(String targetUrl);
    boolean existsUrlByTargetUrl(String targetUrl);
}
