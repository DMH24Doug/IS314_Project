package com.s11160663.prototype_v3.Service;

import com.s11160663.prototype_v3.DTO.RegistrationDTO;
import com.s11160663.prototype_v3.Model.UserEntity;

public interface UserService {
    void saveUser(RegistrationDTO registrationDto);

    void saveDoctor(RegistrationDTO registrationDto);

    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
//    void create_patient(RegistrationDTO registrationDto);
}
