package com.s11160663.prototype_v3.Service.Implementation;

import com.s11160663.prototype_v3.DTO.PrescriptionDTO;
import com.s11160663.prototype_v3.Mapper.PrescriptionMapper;
import com.s11160663.prototype_v3.Model.PrescriptionEntity;
import com.s11160663.prototype_v3.Repository.PrescriptionRepository;
import com.s11160663.prototype_v3.Service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public PrescriptionEntity createPrescription(PrescriptionDTO prescriptionDTO) {
       PrescriptionEntity prescriptionEntity = PrescriptionMapper.mapToPrescription(prescriptionDTO);
       return prescriptionRepository.save(prescriptionEntity);
    }
}
