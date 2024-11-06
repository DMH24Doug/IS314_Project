package com.s11160663.prototype_v3.Service;

import com.s11160663.prototype_v3.DTO.MedicationDTO;
import com.s11160663.prototype_v3.Model.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface MedicationService {

    MedicationEntity createMedication(MedicationDTO medicationDTO);

    MedicationEntity createMedicationIfNotExists(String name);

    List<MedicationDTO> getAllMedications();
    MedicationDTO getMedicationById(Long id);

    MedicationDTO getMedicationByName(String name);

    List<MedicationDTO> searchMedicationsByName(String name);
    void deleteMedication(Long id);
}
