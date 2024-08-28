package com.s11160663.prototype_v3.Mapper;

import com.s11160663.prototype_v3.DTO.DoctorDTO;
import com.s11160663.prototype_v3.Model.DoctorEntity;

public class DoctorMapper {

    //translate employee data objects into employee details
    public static DoctorEntity mapToPatient(DoctorDTO doctorDTO) {

        return DoctorEntity.builder()
                .id(doctorDTO.getId())
                .doctor_fname(doctorDTO.getDoctor_fname())
                .doctor_lname(doctorDTO.getDoctor_lname())
                .createdBy(doctorDTO.getCreatedBy())
                .build();
    }
    //returns employee details from employee to employee data objects
    public static DoctorDTO mapToPatientDTO(DoctorEntity doctor) {

        return DoctorDTO.builder()
                .id(doctor.getId())
                .doctor_fname(doctor.getDoctor_fname())
                .doctor_lname(doctor.getDoctor_lname())
                .createdBy(doctor.getCreatedBy())
                .build();
    }
}
