package com.example.lab04blog;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.managedBlog.id = :blogId")
    List<User> findAllUsersByBlogId(Long blogId);
    @Query("DELETE FROM User u WHERE u.managedBlog.id = :blogId")
    @Modifying
    @Transactional
    void removeAllUsersByBlogId( Long blogId);
}
