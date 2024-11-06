package com.s11160663.prototype_v3.Service.Implementation;

import com.s11160663.prototype_v3.DTO.MedicalExaminationDTO;
import com.s11160663.prototype_v3.Mapper.MedicalExaminationMapper;
import com.s11160663.prototype_v3.Model.MedicalExaminationEntity;
import com.s11160663.prototype_v3.Model.PatientEntity;
import com.s11160663.prototype_v3.Repository.MedicalExaminationRepository;
import com.s11160663.prototype_v3.Repository.PatientRepository;
import com.s11160663.prototype_v3.Service.DoctorService;
import com.s11160663.prototype_v3.Service.MedicalExaminationService;
import com.s11160663.prototype_v3.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


import static com.s11160663.prototype_v3.Mapper.MedicalExaminationMapper.mapToMedicalExaminationDTO;

@Service
public class MedicalExaminationServiceImpl implements MedicalExaminationService {

    private final MedicalExaminationRepository medicalExaminationRepository;
    private MedicalExaminationRepository examinationRepository;
    private PatientService patientService;
    private DoctorService doctorService;
    private PatientRepository patientRepository;

    @Autowired
    public MedicalExaminationServiceImpl(MedicalExaminationRepository examinationRepository
            , MedicalExaminationRepository medicalExaminationRepository, PatientService patientService,PatientRepository patientRepository) {
        this.examinationRepository = examinationRepository;
        this.medicalExaminationRepository = medicalExaminationRepository;
        this.patientService = patientService;
        patientRepository = patientRepository;
    }


    @Override
    public MedicalExaminationEntity createExamination(MedicalExaminationDTO examinationDTO) {
        PatientEntity patient = patientService.findById(examinationDTO.getPatientId());
        MedicalExaminationEntity examinationEntity = MedicalExaminationMapper.mapToMedicalExamination(examinationDTO);
        examinationEntity.setPatient(patient);
        return examinationRepository.save(examinationEntity);

    }

    @Override
    public List<MedicalExaminationDTO> getAll() {
        List<MedicalExaminationEntity> medicalExaminationEntities = medicalExaminationRepository.findAll();
        return medicalExaminationEntities.stream().map(MedicalExaminationMapper::mapToMedicalExaminationDTO).collect(Collectors.toList());
    }




    //todo method to edit
    @Override
    public MedicalExaminationDTO getExaminationById(Long id) {
        MedicalExaminationEntity examinationEntity = examinationRepository.findById(id).get();
        return mapToMedicalExaminationDTO(examinationEntity);
    }


    //method to delete
    @Override
    public void deleteExamination(Long id) {
        if (examinationRepository.existsById(id)) {
            examinationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Examination not found with id: " + id);
        }
    }

    @Override
    public List<MedicalExaminationEntity> findByPatientId(Long patientId) {
        // Fetch the medical examination entities from the repository
        List<MedicalExaminationEntity> medExEntities = medicalExaminationRepository.findByPatientId(patientId);

        // Map each MedicalExaminationEntity to MedicalExaminationDTO
        return (List<MedicalExaminationEntity>) mapToMedicalExaminationDTO(medExEntities.get(Math.toIntExact(patientId)));
    }

    @Override
    public List<MedicalExaminationDTO> getByPatientId(Long patientId) {
        return medicalExaminationRepository.findByPatientId(patientId).stream()
                .map(MedicalExaminationMapper::mapToMedicalExaminationDTO)
                .collect(Collectors.toList());
    }

}
