package com.s11160663.prototype_v3.Controller;

import com.s11160663.prototype_v3.DTO.DoctorDTO;
import com.s11160663.prototype_v3.DTO.PatientDTO;
import com.s11160663.prototype_v3.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DoctorController {

    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctor/doctor_home/{Id}")
    public String patient_save(@PathVariable("Id") Long id, Model model) {
        DoctorDTO doctorDTO = doctorService.findDoctorById(id);
        model.addAttribute("doctor", doctorDTO);

        //return view for patient home
        return "doctor_home";
    }


    //TODO add controls and method for doctor to view patientList


    //TODO add controls and method for doctor to view patientSchedules && patientPrescription

    //TODO add controls and method for doctors to respond to schedules and auto genPrescriptions
}
