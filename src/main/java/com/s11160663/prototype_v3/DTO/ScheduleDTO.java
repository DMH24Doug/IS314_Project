package com.s11160663.prototype_v3.DTO;

import com.s11160663.prototype_v3.Model.PatientEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class ScheduleDTO {
    private Long id;
    private String name;

    private String email;
    private String phone;
    private LocalDate date;
    private LocalTime time;
    private String timeInterval;
    private String message;
    private String attend;
    private List<PatientEntity> patients;

}
