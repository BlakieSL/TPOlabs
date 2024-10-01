package com.example.lab04blog;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Query("SELECT a FROM Article a WHERE a.author.id = :authorId")
    List<Article> findAllArticlesByAuthorId(Long authorId);
    @Query("DELETE FROM Article a WHERE a.author.id = :authorId")
    @Modifying
    @Transactional
    void removeAllArticlesByAuthorId( Long authorId);


}
