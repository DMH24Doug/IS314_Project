package com.s11160663.prototype_v3.Service.Implementation;

import com.s11160663.prototype_v3.DTO.PatientDTO;
import com.s11160663.prototype_v3.Mapper.PatientMapper;
import com.s11160663.prototype_v3.Model.PatientEntity;
import com.s11160663.prototype_v3.Model.UserEntity;
import com.s11160663.prototype_v3.Repository.PatientRepository;
import com.s11160663.prototype_v3.Repository.UserRepository;
import com.s11160663.prototype_v3.Security.SecurityUtil;
import com.s11160663.prototype_v3.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.s11160663.prototype_v3.Mapper.PatientMapper.mapToPatientDTO;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private UserRepository userRepository;


    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;

    }

    @Override
    public List<PatientDTO> findAllPatients() {
        List<PatientEntity> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::mapToPatientDTO).collect(Collectors.toList());
    }

    @Override
    public PatientEntity savePatient(PatientDTO patientDTO) {
        // Fetch the currently logged-in username from the session
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByName(username).orElse(null);

        if (user == null) {
            throw new IllegalStateException("User not found in the database");
        }

        // Map the PatientDTO to a PatientEntity
        PatientEntity patient = PatientMapper.mapToPatient(patientDTO);

        // Set the user to the patient entity
        patient.setUser(user);
        // Set the patient to the user entity (for bi-directional relationship)
        user.setPatient(patient);

        // Save and return the patient entity
        return patientRepository.save(patient);
    }


    @Override
    public PatientDTO findPatientById(Long id) {

        PatientEntity patient = patientRepository.findById(id).get();
        return mapToPatientDTO(patient);
    }

    @Override
    public void deletePatientById(Long id) {

    }

    @Override
    public void updatePatient(PatientDTO patientDTO) {
        PatientEntity patient = PatientMapper.mapToPatient(patientDTO);
        patientRepository.save(patient);

    }

    @Override
    public PatientEntity findById(Long id) {
        PatientEntity patient = patientRepository.findById(id).get();
        return patient;
    }


}
