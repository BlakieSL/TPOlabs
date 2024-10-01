package com.example.lab04blog;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogRepository extends CrudRepository<Blog, Long> {
    @Query("SELECT b FROM Blog b WHERE b.manager.id = :userId")
    List<Blog> findAllBlogsByUserId(Long userId);
    @Query("DELETE FROM Blog b WHERE b.manager.id = :userId")
    @Modifying
    @Transactional
    void removeAllBlogsByUserId( Long userId);

}
