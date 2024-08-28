package com.s11160663.prototype_v3.Service.Implementation;

import com.s11160663.prototype_v3.Model.Roles;
import com.s11160663.prototype_v3.Repository.RoleRepository;
import com.s11160663.prototype_v3.Service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    RoleRepository rolesRepository;

    @Override
    public Roles createRoleIfNotExists(String roleName) {
        if (!rolesRepository.existsByName(roleName)) {
            Roles role = Roles.builder().name(roleName).build();
            return rolesRepository.save(role);
        }
        return rolesRepository.findByName(roleName);
    }
    @Override
    public Roles findByName(String name) {
        return null;
    }

    @Override
    public List<Roles> findAllRoles() {
        return List.of();
    }


}
