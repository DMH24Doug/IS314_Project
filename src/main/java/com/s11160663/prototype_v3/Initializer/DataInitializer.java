package com.s11160663.prototype_v3.Initializer;


import com.s11160663.prototype_v3.Model.Roles;
import com.s11160663.prototype_v3.Model.UserEntity;
import com.s11160663.prototype_v3.Repository.RoleRepository;
import com.s11160663.prototype_v3.Repository.UserRepository;
import com.s11160663.prototype_v3.Service.RolesService;
import com.s11160663.prototype_v3.Service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RolesService rolesService;
    private UserService userService;
    @Autowired
    private UserRepository userRepository;



    @Override
    public void run(String... args) throws Exception {

        //creates roles in database on run

        Roles admin = rolesService.createRoleIfNotExists("ADMIN");
        Roles employee = rolesService.createRoleIfNotExists("EMPLOYEE");
        Roles patient = rolesService.createRoleIfNotExists("PATIENT");


    }



}
