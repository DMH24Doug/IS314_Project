package com.s11160663.prototype_v3.Mapper;

import com.s11160663.prototype_v3.DTO.PrescriptionDTO;
import com.s11160663.prototype_v3.Model.PrescriptionEntity;

public class PrescriptionMapper {

    public static PrescriptionEntity mapToPrescription(PrescriptionDTO prescriptionDTO) {

        return PrescriptionEntity.builder()
                .id(prescriptionDTO.getId())
                .name(prescriptionDTO.getName())
                .dosage(prescriptionDTO.getDosage())
                .frequency(prescriptionDTO.getFrequency())
                .description(prescriptionDTO.getDescription())
                .build();
    }

    public static PrescriptionDTO mapToPrescriptionDTO(PrescriptionEntity prescriptionEntity) {
        return PrescriptionDTO.builder()
                .id(prescriptionEntity.getId())
                .name(prescriptionEntity.getName())
                .dosage(prescriptionEntity.getDosage())
                .frequency(prescriptionEntity.getFrequency())
                .description(prescriptionEntity.getDescription())
                .build();
    }
}
