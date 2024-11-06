package com.s11160663.prototype_v3.DTO;

import com.s11160663.prototype_v3.Model.UserEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
@Data
public class PatientDTO {

    private Long id;
    private String patient_fname;
    private String patient_lname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String profileImagePath;
    private UserEntity user;


}
