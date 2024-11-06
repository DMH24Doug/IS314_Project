package com.s11160663.prototype_v3.Mapper;

import com.s11160663.prototype_v3.DTO.DoctorDTO;
import com.s11160663.prototype_v3.Model.DoctorEntity;

public class DoctorMapper {
    //TODO update doctor mapper to current model attributes
    //translate employee data objects into employee details
    public static DoctorEntity mapToDoctor(DoctorDTO doctorDTO) {

        return DoctorEntity.builder()
                .id(doctorDTO.getId())
                .doctor_fname(doctorDTO.getDoctor_fname())
                .doctor_lname(doctorDTO.getDoctor_lname())
                .gender(doctorDTO.getGender())
                .dateOfBirth(doctorDTO.getDateOfBirth())
                .phoneNumber(doctorDTO.getPhoneNumber())
                .address(doctorDTO.getAddress())
                .emergencyContactPhone(doctorDTO.getEmergencyContactPhone())
                .emergencyContactName(doctorDTO.getEmergencyContactName())
                .user(doctorDTO.getUser_id())
                .profileImagePath(doctorDTO.getProfileImagePath())
                .build();
    }
    //returns employee details from employee to employee data objects
    public static DoctorDTO mapToDoctorDTO(DoctorEntity doctor) {

        return DoctorDTO.builder()
                .id(doctor.getId())
                .doctor_fname(doctor.getDoctor_fname())
                .doctor_lname(doctor.getDoctor_lname())
                .gender(doctor.getGender())
                .dateOfBirth(doctor.getDateOfBirth())
                .address(doctor.getAddress())
                .phoneNumber(doctor.getPhoneNumber())
                .emergencyContactName(doctor.getEmergencyContactName())
                .emergencyContactPhone(doctor.getEmergencyContactPhone())
                .user_id(doctor.getUser())
                .profileImagePath(doctor.getProfileImagePath())
                .build();
    }
}
