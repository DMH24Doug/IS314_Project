package com.s11160663.prototype_v3.Repository;

import com.s11160663.prototype_v3.Model.MedicalExaminationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalExaminationRepository extends JpaRepository<MedicalExaminationEntity, Long> {
    List<MedicalExaminationEntity> findByPatientId(Long patientId);
}
