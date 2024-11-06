package com.s11160663.prototype_v3.Repository;

import com.s11160663.prototype_v3.Model.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    PatientEntity findByUserId(Long userId);

}
