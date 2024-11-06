package com.s11160663.prototype_v3.Repository;

import com.s11160663.prototype_v3.Model.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<MedicationEntity, Long> {
    List<MedicationEntity> findByNameContaining(String name);
    boolean existsByName(String name);
    MedicationEntity findByName(String name);
}
