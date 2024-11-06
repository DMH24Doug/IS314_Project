package com.s11160663.prototype_v3.Service;


import com.s11160663.prototype_v3.DTO.PrescriptionDTO;
import com.s11160663.prototype_v3.Model.PrescriptionEntity;

public interface PrescriptionService {

    PrescriptionEntity createPrescription(PrescriptionDTO prescriptionDTO);
}
