package com.s11160663.prototype_v3.Service.Implementation;

import com.s11160663.prototype_v3.DTO.MedicationDTO;
import com.s11160663.prototype_v3.Mapper.MedicationMapper;
import com.s11160663.prototype_v3.Model.MedicationEntity;
import com.s11160663.prototype_v3.Repository.MedicationRepository;
import com.s11160663.prototype_v3.Service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


import static com.s11160663.prototype_v3.Mapper.MedicationMapper.mapToMedicationDTO;

@Service
public class MedicationServiceImpl implements MedicationService {

    private MedicationRepository medicationRepository;

    @Autowired
    public MedicationServiceImpl(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    public MedicationEntity createMedication(MedicationDTO medicationDTO) {
        MedicationEntity medication = MedicationMapper.mapToMedication(medicationDTO);
        return medicationRepository.save(medication);
    }

    @Override
    public MedicationEntity createMedicationIfNotExists(String name) {
        // Check if the medication already exists
        if (!medicationRepository.existsByName(name)) {
            // Create a new medication if it doesn't exist
            MedicationEntity medication = MedicationEntity.builder()
                    .name(name)
                    .build();
            // Save and return the new medication as DTO
            return medicationRepository.save(medication);
        }
        // If it exists, return the existing medication as DTO
        return medicationRepository.findByName(name);
    }

    @Override
    public List<MedicationDTO> getAllMedications() {
        List<MedicationEntity> medication = medicationRepository.findAll();
        return medication.stream()
                .map(MedicationMapper::mapToMedicationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicationDTO getMedicationById(Long id) {
        MedicationEntity medication = medicationRepository.findById(id).get();
        return mapToMedicationDTO(medication);
    }

    @Override
    public MedicationDTO getMedicationByName(String name){
        MedicationEntity medication = medicationRepository.findByName(name);
        return mapToMedicationDTO(medication);
    }

    //todo
    @Override
    public List<MedicationDTO> searchMedicationsByName(String name) {
        return List.of();
    }

    @Override
    public void deleteMedication(Long id) {
        if (medicationRepository.existsById(id)) {
            medicationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Examination not found with id: " + id);
        }
    }


}
