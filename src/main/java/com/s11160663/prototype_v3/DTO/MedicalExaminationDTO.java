package com.s11160663.prototype_v3.DTO;

import com.s11160663.prototype_v3.Model.MedicationEntity;
import com.s11160663.prototype_v3.Model.PatientEntity;
import com.s11160663.prototype_v3.Model.PrescriptionEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class MedicalExaminationDTO {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfExamination;
    private String medicalHistory;
    private List<PrescriptionEntity> prescriptions;

    // Vital Signs
    private double height;
    private double weight;
    private double respiratoryRate;
    private String bloodPressure;
    private double heartRate;
    private double pulseRate;
    private String pulseRhythmRegularity;
    private double systolicBP1;
    private double diastolicBP1;
    private double systolicBP2;
    private double diastolicBP2;
    private Long patientId;
    private Long doctorId;


}
