package com.s11160663.prototype_v3.Mapper;

import com.s11160663.prototype_v3.DTO.MedicationDTO;
import com.s11160663.prototype_v3.Model.MedicationEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationMapper {
    //builds and maps medication Data objects into medication entity
    public static MedicationEntity mapToMedication(MedicationDTO medicationDTO) {

        return MedicationEntity.builder()
                .id(medicationDTO.getId())
                .name(medicationDTO.getName())
                .build();


    }
    //builds and maps medication entity into medication Data objects
    public static MedicationDTO mapToMedicationDTO(MedicationEntity medicationEntity) {

        return MedicationDTO.builder()
                .id(medicationEntity.getId())
                .name(medicationEntity.getName())
                .build();
    }
}
