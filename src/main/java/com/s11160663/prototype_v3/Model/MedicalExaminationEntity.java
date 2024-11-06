package com.s11160663.prototype_v3.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "medicalExamination")

public class MedicalExaminationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateOfExamination;
    
    @Lob
    private String medicalHistory;

    // One examination can have multiple prescriptions
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "examination_id")
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


    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientEntity patient;

    // Many-to-Many with DoctorEntity
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "examination_doctors",
            joinColumns = @JoinColumn(name = "examination_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private DoctorEntity doctors;

}
