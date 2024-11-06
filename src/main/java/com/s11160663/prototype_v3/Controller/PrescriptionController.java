package com.s11160663.prototype_v3.Controller;

import com.s11160663.prototype_v3.DTO.MedicalExaminationDTO;
import com.s11160663.prototype_v3.DTO.MedicationDTO;
import com.s11160663.prototype_v3.DTO.PrescriptionDTO;
import com.s11160663.prototype_v3.Model.MedicalExaminationEntity;
import com.s11160663.prototype_v3.Model.PrescriptionEntity;
import com.s11160663.prototype_v3.Service.MedicalExaminationService;
import com.s11160663.prototype_v3.Service.MedicationService;
import com.s11160663.prototype_v3.Service.PrescriptionService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PrescriptionController {

    private MedicalExaminationService medicalExaminationService;
    private MedicationService medicationService;
    private PrescriptionService prescriptionService;


    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService, MedicalExaminationService medicalExaminationService, MedicationService medicationService) {
        this.medicalExaminationService = medicalExaminationService;
        this.medicationService = medicationService;
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("/prescription/create")
    public String createExamination(@NotNull Model model) {
        // Create a new medical examination object

        PrescriptionEntity prescription = new PrescriptionEntity();
        model.addAttribute("prescription", prescription);

        // Fetch all medications from the database
        List<MedicationDTO> medications = medicationService.getAllMedications();
        model.addAttribute("medications", medications); // Pass medications to the template

        return "medical_exam"; // Render the Thymeleaf template
    }

    @PostMapping("/prescription/create")
    public String createExamination(@ModelAttribute("medExam") MedicalExaminationDTO medicalExaminationDTO,
                                    @ModelAttribute("prescription") PrescriptionDTO prescriptionDTO) {
        // Save the medical examination & prescription
        medicalExaminationService.createExamination(medicalExaminationDTO);
        prescriptionService.createPrescription(prescriptionDTO);

        // TODO: Redirect to the doctor’s home page with the relevant ID
        return "redirect:/doctor/doctor_home/{Id}";
    }
}
