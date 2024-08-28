package com.s11160663.prototype_v3.Service;

import com.s11160663.prototype_v3.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesService {
    Roles createRoleIfNotExists(String roleName);
    Roles findByName(String roleName);
    List<Roles> findAllRoles();
}
