package com.springboot.architectural.repository;

import com.springboot.architectural.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByName(String name);
//              findByName
}
