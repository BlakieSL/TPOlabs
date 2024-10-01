package com.example.lab04blog;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query("SELECT r FROM Role r JOIN r.users u WHERE u.id = :userId")
    List<Role> findAllRolesByUserId( Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Role r WHERE r.id = :roleId")
    void removeRoleById( Long roleId);
}
