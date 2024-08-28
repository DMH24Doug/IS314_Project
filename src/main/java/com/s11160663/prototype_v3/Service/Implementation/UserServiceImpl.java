package com.s11160663.prototype_v3.Service.Implementation;

import com.s11160663.prototype_v3.DTO.RegistrationDTO;
import com.s11160663.prototype_v3.Model.Roles;
import com.s11160663.prototype_v3.Model.UserEntity;
import com.s11160663.prototype_v3.Repository.PatientRepository;
import com.s11160663.prototype_v3.Repository.RoleRepository;
import com.s11160663.prototype_v3.Repository.UserRepository;
import com.s11160663.prototype_v3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private PatientRepository patientRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.patientRepository = patientRepository;
    }
    @Override
    public void saveUser(RegistrationDTO registrationDto) {
        UserEntity user = new UserEntity();
        user.setName(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setEmail(registrationDto.getEmail());
        Roles role = roleRepository.findByName("PATIENT");
        user.setRoles(Collections.singletonList(role));


        userRepository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByName(username);
    }


}
