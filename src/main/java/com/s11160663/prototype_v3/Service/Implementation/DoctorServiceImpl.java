package com.s11160663.prototype_v3.Service.Implementation;

import com.s11160663.prototype_v3.DTO.DoctorDTO;
import com.s11160663.prototype_v3.Mapper.DoctorMapper;
import com.s11160663.prototype_v3.Mapper.PatientMapper;
import com.s11160663.prototype_v3.Model.DoctorEntity;
import com.s11160663.prototype_v3.Model.PatientEntity;
import com.s11160663.prototype_v3.Model.UserEntity;
import com.s11160663.prototype_v3.Repository.DoctorRepository;
import com.s11160663.prototype_v3.Repository.UserRepository;
import com.s11160663.prototype_v3.Security.SecurityUtil;
import com.s11160663.prototype_v3.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.s11160663.prototype_v3.Mapper.DoctorMapper.mapToDoctorDTO;

@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;
    private UserRepository userRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<DoctorDTO> findAllDoctors() {
        List<DoctorEntity> doctorEntities = doctorRepository.findAll();
        return doctorEntities.stream().map(DoctorMapper::mapToDoctorDTO).collect(Collectors.toList());
    }

    //creating and saving doctor
    @Override
    public DoctorEntity saveDoctor(DoctorDTO doctorDTO) {
        // Fetch the currently logged-in username from the session
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByName(username).orElse(null);

        if (user == null) {
            throw new IllegalStateException("User not found in the database");
        }

        // Map the DoctorDTO to a DoctorEntity
        DoctorEntity doctor = DoctorMapper.mapToDoctor(doctorDTO);

        // Set the user to the doctor entity
        doctor.setUser(user);
        // Set the doctor to the user entity (for bi-directional relationship)
        user.setDoctor(doctor);

        // Save and return the doctor entity
        return doctorRepository.save(doctor);
    }

    //TODO update method for doctor
    @Override
    public DoctorEntity updateDoctor(DoctorDTO doctorDTO) {
        return null;
    }

    //TODO delete method for doctor
    @Override
    public DoctorEntity deleteDoctor(DoctorDTO doctorDTO) {
        return null;
    }

    @Override
    public DoctorDTO findDoctorById(Long id) {
        DoctorEntity doctor = doctorRepository.findById(id).get();
        return mapToDoctorDTO(doctor);
    }
}
