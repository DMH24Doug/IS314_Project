package com.s11160663.prototype_v3.Controller;

import com.s11160663.prototype_v3.DTO.PatientDTO;
import com.s11160663.prototype_v3.Model.PatientEntity;
import com.s11160663.prototype_v3.Repository.PatientRepository;
import com.s11160663.prototype_v3.Service.PatientService;
import com.s11160663.prototype_v3.Service.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PatientController {

    private PatientService patientService;
    private UserService userService;

    @Autowired
    public PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientService patientService, UserService userService) {
        this.patientService = patientService;
        this.userService = userService;
    }


    //handles patient list
    @GetMapping("/patient")
    public String patient(@NotNull Model model) {
        List<PatientDTO> list = patientService.findAllPatients();
        model.addAttribute("patient", list);

        return "patientList";
    }

    //build create patient REST API
    @GetMapping("/patient/create")
    public String patient_create(@NotNull Model model) {
        PatientEntity patient = new PatientEntity();
        model.addAttribute("patient", patient);

        return "create_patient";
    }
    @PostMapping("/patient/create")
    public String create(@ModelAttribute("patient") PatientDTO patientDTO) {
        PatientEntity savedPatient = patientService.savePatient(patientDTO);

        return "redirect:/patient/patient_home/" + savedPatient.getId();
    }

    @GetMapping("/patient/patient_home/{Id}")
    public String patient_save(@PathVariable("Id") Long Id, @NotNull Model model) {
        PatientDTO patientDTO = patientService.findPatientById(Id);

        if(patientDTO != null) {
            model.addAttribute("patient", patientDTO);
        }
        //return view for patient home
        return "patient_home";
    }






}
