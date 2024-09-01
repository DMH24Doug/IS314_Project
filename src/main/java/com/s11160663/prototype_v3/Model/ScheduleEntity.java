package com.s11160663.prototype_v3.Model;

import ch.qos.logback.core.model.Model;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String email;
    private String phone;
    private LocalDate date;
    private LocalTime time;
    private String timeInterval;
    private String message;
    private String attend;

    @ManyToMany
    @JoinTable(
            name="patient_schedules",
            joinColumns={@JoinColumn(name="PATIENT_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="SCHEDULE_ID", referencedColumnName="ID")})
    private List<PatientEntity> patient = new ArrayList<>();
}
