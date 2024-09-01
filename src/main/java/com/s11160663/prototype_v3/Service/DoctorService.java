package com.s11160663.prototype_v3.Service;

import com.s11160663.prototype_v3.DTO.DoctorDTO;
import com.s11160663.prototype_v3.Model.DoctorEntity;

import java.util.List;

public interface DoctorService {

    List<DoctorDTO> findAllDoctors();
    DoctorEntity saveDoctor(DoctorDTO doctorDTO);
    DoctorEntity updateDoctor(DoctorDTO doctorDTO);
    DoctorEntity deleteDoctor(DoctorDTO doctorDTO);
    DoctorDTO findDoctorById(Long id);
}
