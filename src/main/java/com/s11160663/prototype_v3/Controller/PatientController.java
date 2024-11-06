package com.s11160663.prototype_v3.Controller;


import com.s11160663.prototype_v3.DTO.MedicalExaminationDTO;
import com.s11160663.prototype_v3.DTO.PatientDTO;
import com.s11160663.prototype_v3.Model.PatientEntity;
import com.s11160663.prototype_v3.Repository.PatientRepository;
import com.s11160663.prototype_v3.Repository.UserRepository;
import com.s11160663.prototype_v3.Service.MedicalExaminationService;
import com.s11160663.prototype_v3.Service.PatientService;
import com.s11160663.prototype_v3.Service.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
public class PatientController {

    private PatientService patientService;
    private UserService userService;
    private UserRepository userRepository;
    MedicalExaminationService medicalExaminationService;

    @Autowired
    public PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientService patientService, UserService userService
            , UserRepository userRepository, MedicalExaminationService medicalExaminationService) {
        this.patientService = patientService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.medicalExaminationService = medicalExaminationService;
    }


    //handles patient list
    @GetMapping("/patient")
    public String patient(@NotNull Model model) {
        List<PatientDTO> list = patientService.findAllPatients();
        model.addAttribute("patient", list);

        return "redirect:/patient/create";
    }

    //build create patient REST API
    @GetMapping("/patient/create")
    public String patient_create(@NotNull Model model) {
        PatientEntity patient = new PatientEntity();
        model.addAttribute("patient", patient);

        return "create_patient";
    }


    @PostMapping("/patient/create")
    public String create(@ModelAttribute("patient") PatientDTO patientDTO,
                         @RequestParam("imageFile") MultipartFile imageFile) {

        try {
            // Check if the file size is acceptable (e.g., < 2 MB)
            if (imageFile.getSize() > 2 * 1024 * 1024) {  // 2 MB limit
                throw new IllegalArgumentException("File is too large. Maximum size allowed is 2MB.");
            }

            // Directory where the image will be saved
            String uploadDir = "uploads/patient-profile-images/";

            // Create the directory if it doesn't exist
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Generate a unique filename using UUID
            String originalFilename = imageFile.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

            // Save the file to the server
            Path filePath = Paths.get(uploadDir + uniqueFilename);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Set the file path in the DTO
            patientDTO.setProfileImagePath(filePath.toString());

            // Save the patient information
            PatientEntity savedPatient = patientService.savePatient(patientDTO);

            return "redirect:/patient/patient_home/" + savedPatient.getId();

        } catch (IOException e) {
            e.printStackTrace();
            return "error"; // Redirect to an error page if upload fails
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "redirect:/patient/create?error=fileTooLarge";
        }
    }


    @GetMapping("/patient/patient_home/{id}")
    public String patientProfile(@PathVariable("id") Long id, Model model) {
        List<MedicalExaminationDTO> healthRecords = medicalExaminationService.getByPatientId(id);
        model.addAttribute("medicalEx", healthRecords);

        // Add patient details to the model for Thymeleaf rendering
        // Fetch the patient data (name, phone, etc.) from the service layer
        PatientDTO patientDTO = patientService.findPatientById(id);
        model.addAttribute("patient", patientDTO);


        // Return the view for the patient home page
        return "patient_home";
    }

    @GetMapping("/patient/image/{id}")
    @ResponseBody
    public ResponseEntity<Resource> getPatientImage(@PathVariable("id") Long id) {
        PatientDTO patientDTO = patientService.findPatientById(id);

        String profileImagePath = patientDTO.getProfileImagePath();  // Retrieve the path

        try {
            if (profileImagePath != null) {
                // Load the image as a resource
                Path imagePath = Paths.get(profileImagePath);
                Resource imageResource = new UrlResource(imagePath.toUri());

                if (imageResource.exists() && imageResource.isReadable()) {
                    // Determine the content type
                    String contentType = Files.probeContentType(imagePath);
                    MediaType mediaType = contentType != null ? MediaType.parseMediaType(contentType) : MediaType.IMAGE_JPEG;

                    // Return the image as a ResponseEntity<Resource>
                    return ResponseEntity.ok()
                            .contentType(mediaType)
                            .body(imageResource);
                } else {
                    // If the image does not exist or is not readable, return 404
                    return ResponseEntity.notFound().build();
                }
            } else {
                // If no image path is found, return 404
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Return 500 Internal Server Error if there is an issue reading the file
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    //TODO methods to view health records
    @GetMapping("/patient/health_record")
    public String patient_health_record(Model model) {
        return "patientHealth_records";
    }
    //TODO methods for requesting appointment
    @GetMapping("/patient/request_schedule")
    public String request_schedule(Model model) {
        return "request";
    }

    //TODO methods for appointment
    @GetMapping("/patient/appointment")
    public String appointment(Model model) {
        return "patient_appointment";
    }



    //TODO make it return medExam only for specific patient
    //patient chart
    @GetMapping("/patient/health-chart/{patientId}")
    public String getPatientHealthData(@PathVariable Long patientId, Model model) {
        // Fetch the patient's health records (e.g., medical exams over time)
        PatientDTO patientDTO = patientService.findPatientById(patientId);
        model.addAttribute("patient", patientDTO);
        List<MedicalExaminationDTO> healthRecords = medicalExaminationService.getByPatientId(patientId);
        // Add records to the model for Thymeleaf to use
        model.addAttribute("healthRecords", healthRecords);

        return "health_chart"; // Render the Thymeleaf template
    }
}
