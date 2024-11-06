package com.s11160663.prototype_v3.Controller;

import com.s11160663.prototype_v3.DTO.MedicalExaminationDTO;
import com.s11160663.prototype_v3.DTO.MedicationDTO;
import com.s11160663.prototype_v3.DTO.PatientDTO;
import com.s11160663.prototype_v3.DTO.PrescriptionDTO;
import com.s11160663.prototype_v3.Model.*;
import com.s11160663.prototype_v3.Repository.DoctorRepository;
import com.s11160663.prototype_v3.Repository.PatientRepository;
import com.s11160663.prototype_v3.Repository.UserRepository;
import com.s11160663.prototype_v3.Service.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MedicalExaminationController {
    private final DoctorRepository doctorRepository;
    //todo fix controls

    private MedicalExaminationService medicalExaminationService;
    private MedicationService medicationService;
    private PrescriptionService prescriptionService;
    private PatientRepository patientRepository;
    private UserService userService;


    @Autowired
    public MedicalExaminationController(PrescriptionService prescriptionService,
                                        MedicalExaminationService medicalExaminationService,
                                        MedicationService medicationService, PatientRepository patientRepository, DoctorRepository doctorRepository
            , UserService userService) {
        this.patientRepository = patientRepository;
        this.medicalExaminationService = medicalExaminationService;
        this.medicationService = medicationService;
        this.prescriptionService = prescriptionService;
        this.doctorRepository = doctorRepository;
        this.userService = userService;

    }

    @GetMapping("/examination/create/{Id}")
    public String createExamination(@PathVariable("Id")Long Id, Model model) {

        PatientEntity patient = patientRepository.findById(Id).get();
        // Create a new medical examination object
        MedicalExaminationEntity medicalExamination = new MedicalExaminationEntity();
        medicalExamination.setPatient(patient);
        model.addAttribute("medExam", medicalExamination);

        //create new prescription
        PrescriptionEntity prescription = new PrescriptionEntity();
        model.addAttribute("prescription", prescription);

        // Fetch all medications from the database
        List<MedicationDTO> medications = medicationService.getAllMedications();
        model.addAttribute("medications", medications); // Pass medications to the template

        return "medical_exam"; // Render the Thymeleaf template
    }

    @PostMapping("/examination/create")
    public String createExamination(@ModelAttribute("medExam") MedicalExaminationDTO medicalExaminationDTO) {
        // Save the medical examination & prescription
        medicalExaminationService.createExamination(medicalExaminationDTO);

        //returns back to doctor
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        UserEntity user = userService.findByUsername(username);
        DoctorEntity doctorId = user.getDoctor();


        // TODO: Redirect to the doctor’s home page with the relevant ID
        return "redirect:/doctor/doctor_home/" + doctorId.getId();
    }





}

