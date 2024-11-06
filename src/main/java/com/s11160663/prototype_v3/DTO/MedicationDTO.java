package com.s11160663.prototype_v3.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationDTO {

    private Long id;
    private String name;

}
