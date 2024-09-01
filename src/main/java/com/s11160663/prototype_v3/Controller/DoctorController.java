package com.s11160663.prototype_v3.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorController {

    @GetMapping("/doctor")
    public String doctor() {
        return "doctor_home";
    }


    //TODO add controls and method for doctor to view patientList

    //TODO add controls and method for doctor to view patientSchedules && patientPrescription

    //TODO add controls and method for doctors to respond to schedules and auto genPrescriptions
}
