package com.s11160663.prototype_v3.DTO;

import com.s11160663.prototype_v3.Model.UserEntity;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PatientDTO {

    private Long id;
    private String patient_fname;
    private String patient_lname;
    private UserEntity createdBy;

}
