package com.s11160663.prototype_v3.Model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
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
@Table(name = "patient")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String patient_fname;
    private String patient_lname;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;
    private String emergencyContactName;
    private String emergencyContactPhone;


    private String profileImagePath;  // Field to store the uploaded image as binary
    //1-1 relationship to user Entity
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    //M-M relationship to examination Entity
    @OneToMany(mappedBy="patient" , cascade = CascadeType.ALL)
    private List<MedicalExaminationEntity> examinations = new ArrayList<>();

    //M-M relationship to schedules Entity
    @OneToMany(mappedBy="patient")
    private List<ScheduleEntity> schedules = new ArrayList<>();


}


