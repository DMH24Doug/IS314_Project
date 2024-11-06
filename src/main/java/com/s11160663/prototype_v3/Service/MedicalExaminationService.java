package com.s11160663.prototype_v3.Service;

import com.s11160663.prototype_v3.DTO.MedicalExaminationDTO;
import com.s11160663.prototype_v3.Model.MedicalExaminationEntity;

import java.util.List;

public interface MedicalExaminationService {
    MedicalExaminationEntity createExamination(MedicalExaminationDTO examinationDTO);
    List<MedicalExaminationDTO> getAll();
    MedicalExaminationDTO getExaminationById(Long id);
    void deleteExamination(Long id);


    List<MedicalExaminationEntity> findByPatientId(Long patientId);

    List<MedicalExaminationDTO> getByPatientId(Long patientId);

}
