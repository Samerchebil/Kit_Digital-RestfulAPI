package com.example.kit_digital.Repo;

import com.example.kit_digital.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    @Query("SELECT s FROM Role s WHERE s.name=?1")
    Role findByName(String name);

}
