package com.s11160663.prototype_v3.DTO;

import com.s11160663.prototype_v3.Model.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorDTO {

    private Long id;
    private String doctor_fname;
    private String doctor_lname;
    private UserEntity createdBy;
}
