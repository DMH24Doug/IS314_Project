package com.s11160663.prototype_v3.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "prescriptions")
public class PrescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;


    @ManyToMany
    @JoinTable(
            name="patient_prescriptions",
            joinColumns={@JoinColumn(name="PATIENT_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="PRESCRIPTION_ID", referencedColumnName="ID")})
    private List<PatientEntity> patient = new ArrayList<>();
}
