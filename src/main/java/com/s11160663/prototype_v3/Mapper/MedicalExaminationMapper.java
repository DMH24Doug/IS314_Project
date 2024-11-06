package com.s11160663.prototype_v3.Mapper;

import com.s11160663.prototype_v3.DTO.MedicalExaminationDTO;
import com.s11160663.prototype_v3.DTO.MedicationDTO;
import com.s11160663.prototype_v3.DTO.PatientDTO;
import com.s11160663.prototype_v3.Model.DoctorEntity;
import com.s11160663.prototype_v3.Model.MedicalExaminationEntity;
import com.s11160663.prototype_v3.Model.PatientEntity;
import com.s11160663.prototype_v3.Service.DoctorService;
import com.s11160663.prototype_v3.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicalExaminationMapper {

    private PatientService patientService;
    private DoctorService doctorService;

    @Autowired
    public MedicalExaminationMapper(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }
        //builds and maps medical examination Data objects into medical examination entity
    public static MedicalExaminationEntity mapToMedicalExamination(MedicalExaminationDTO medicalExaminationDTO) {


        return MedicalExaminationEntity.builder()
                .id(medicalExaminationDTO.getId())
                .dateOfExamination(medicalExaminationDTO.getDateOfExamination())
                .medicalHistory(medicalExaminationDTO.getMedicalHistory())
                .prescriptions(medicalExaminationDTO.getPrescriptions())
                .height(medicalExaminationDTO.getHeight())
                .weight(medicalExaminationDTO.getWeight())
                .bloodPressure(medicalExaminationDTO.getBloodPressure())
                .heartRate(medicalExaminationDTO.getHeartRate())
                .pulseRate(medicalExaminationDTO.getPulseRate())
                .pulseRhythmRegularity(medicalExaminationDTO.getPulseRhythmRegularity())
                .respiratoryRate(medicalExaminationDTO.getRespiratoryRate())
                .diastolicBP1(medicalExaminationDTO.getDiastolicBP1())
                .diastolicBP2(medicalExaminationDTO.getDiastolicBP2())
                .systolicBP1(medicalExaminationDTO.getSystolicBP1())
                .systolicBP2(medicalExaminationDTO.getSystolicBP2())
                .build();
    }

    //builds and maps medical examination entity into medical examination Data objects
    public static MedicalExaminationDTO mapToMedicalExaminationDTO(MedicalExaminationEntity medicalExamination) {

        return MedicalExaminationDTO.builder()
                .id(medicalExamination.getId())
                .dateOfExamination(medicalExamination.getDateOfExamination())
                .medicalHistory(medicalExamination.getMedicalHistory())
                .prescriptions(medicalExamination.getPrescriptions())
                .height(medicalExamination.getHeight())
                .weight(medicalExamination.getWeight())
                .bloodPressure(medicalExamination.getBloodPressure())
                .heartRate(medicalExamination.getHeartRate())
                .pulseRate(medicalExamination.getPulseRate())
                .pulseRhythmRegularity(medicalExamination.getPulseRhythmRegularity())
                .respiratoryRate(medicalExamination.getRespiratoryRate())
                .diastolicBP1(medicalExamination.getDiastolicBP1())
                .diastolicBP2(medicalExamination.getDiastolicBP2())
                .systolicBP1(medicalExamination.getSystolicBP1())
                .systolicBP2(medicalExamination.getSystolicBP2())
                .patientId(medicalExamination.getPatient().getId())
                .doctorId(medicalExamination.getPatient().getId())
                .build();
    }
}
