package com.s11160663.prototype_v3.Repository;

import com.s11160663.prototype_v3.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByName(String userName);
    UserEntity findFirstByName(String username);

}
