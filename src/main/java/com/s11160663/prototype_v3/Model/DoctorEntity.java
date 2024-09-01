package com.s11160663.prototype_v3.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String doctor_fname;
    private String doctor_lname;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;
    private String emergencyContactName;
    private String emergencyContactPhone;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private UserEntity createdBy;

}
