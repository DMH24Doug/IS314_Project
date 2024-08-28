package com.s11160663.prototype_v3.Repository;

import com.s11160663.prototype_v3.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);
    boolean existsByName(String name);
}