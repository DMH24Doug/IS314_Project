package com.s11160663.prototype_v3.Model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prescriptions")
public class PrescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dosage;
    private String frequency;
    private String description;

    @ManyToOne
    @JoinColumn(name = "exam_id",  referencedColumnName = "id")
    private MedicalExaminationEntity medical_exam;

}
