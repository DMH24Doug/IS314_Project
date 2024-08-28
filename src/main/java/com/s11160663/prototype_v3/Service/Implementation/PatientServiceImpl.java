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
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByName(username);
        PatientEntity patient = PatientMapper.mapToPatient(patientDTO);
        patient.setCreatedBy(user);
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




}
