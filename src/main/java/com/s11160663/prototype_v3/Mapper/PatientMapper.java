package com.s11160663.prototype_v3.Mapper;

import com.s11160663.prototype_v3.DTO.PatientDTO;
import com.s11160663.prototype_v3.Model.PatientEntity;

public class PatientMapper {


    //TODO update patient mapper to current model attributes
    //translate employee data objects into employee details
    public static PatientEntity mapToPatient(PatientDTO patientDTO) {

        return PatientEntity.builder()
                .id(patientDTO.getId())
                .patient_fname(patientDTO.getPatient_fname())
                .patient_lname(patientDTO.getPatient_lname())
                .gender(patientDTO.getGender())
                .dateOfBirth(patientDTO.getDateOfBirth())
                .address(patientDTO.getAddress())
                .phoneNumber(String.valueOf(patientDTO.getPhoneNumber()))
                .emergencyContactName(patientDTO.getEmergencyContactName())
                .emergencyContactName(patientDTO.getEmergencyContactName())
                .user(patientDTO.getUser())
                .build();
    }
    //returns employee details from employee to employee data objects
    public static PatientDTO mapToPatientDTO(PatientEntity patient) {

        return PatientDTO.builder()
                .id(patient.getId())
                .patient_fname(patient.getPatient_fname())
                .patient_lname(patient.getPatient_lname())
                .gender(patient.getGender())
                .dateOfBirth(patient.getDateOfBirth())
                .phoneNumber(String.valueOf(patient.getPhoneNumber()))
                .address(patient.getAddress())
                .emergencyContactName(patient.getEmergencyContactName())
                .emergencyContactPhone(patient.getEmergencyContactPhone())
                .user(patient.getUser())
                .build();
    }
}
