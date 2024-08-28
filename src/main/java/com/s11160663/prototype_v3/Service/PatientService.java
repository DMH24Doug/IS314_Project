package com.s11160663.prototype_v3.Service;


import com.s11160663.prototype_v3.DTO.PatientDTO;
import com.s11160663.prototype_v3.Model.PatientEntity;

import java.util.List;

public interface PatientService {

    List<PatientDTO> findAllPatients();
    PatientEntity savePatient(PatientDTO patientDTO);
    PatientDTO findPatientById(Long id);
    void deletePatientById(Long id);
    void updatePatient(PatientDTO patientDTO);
}
