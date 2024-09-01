package com.s11160663.prototype_v3.DTO;

import com.s11160663.prototype_v3.Model.PatientEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PrescriptionDTO {

    private Long id;
    private String name;
    private String description;
    private List<PatientEntity> patients;
}
