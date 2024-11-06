package com.s11160663.prototype_v3.Controller;

import com.s11160663.prototype_v3.DTO.DoctorDTO;
import com.s11160663.prototype_v3.DTO.PatientDTO;
import com.s11160663.prototype_v3.Model.DoctorEntity;
import com.s11160663.prototype_v3.Service.DoctorService;
import com.s11160663.prototype_v3.Service.PatientService;
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
public class DoctorController {

    private DoctorService doctorService;
    private PatientService patientService;

    @Autowired
    public DoctorController(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    //build create patient REST API
    @GetMapping("/doctor/create")
    public String patient_create(@NotNull Model model) {
        DoctorEntity doctor = new DoctorEntity();
        model.addAttribute("doctor", doctor);

        return "create_doctor";
    }


    @PostMapping("/doctor/create")
    public String create(@ModelAttribute("doctor") DoctorDTO doctorDTO,
                         @RequestParam("imageFile") MultipartFile imageFile) {

        try {
            // Check if the file size is acceptable (e.g., < 2 MB)
            if (imageFile.getSize() > 2 * 1024 * 1024) {  // 2 MB limit
                throw new IllegalArgumentException("File is too large. Maximum size allowed is 2MB.");
            }

            // Directory where the image will be saved
            String uploadDir = "uploads/doctor-profile-images/";

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
            doctorDTO.setProfileImagePath(filePath.toString());

            // Save the patient information
            DoctorEntity savedDoctor = doctorService.saveDoctor(doctorDTO);

            return "redirect:/doctor/doctor_home/" + savedDoctor.getId();

        } catch (IOException e) {
            e.printStackTrace();
            return "error"; // Redirect to an error page if upload fails
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "redirect:/doctor/create?error=fileTooLarge";
        }
    }

    @GetMapping("/doctor/doctor_home/{Id}")
    public String patient_save(@PathVariable("Id") Long id, Model model) {
        DoctorDTO doctorDTO = doctorService.findDoctorById(id);
        model.addAttribute("doctor", doctorDTO);

        //display patients
        List<PatientDTO> patientDTOS = patientService.findAllPatients();
        model.addAttribute("patient", patientDTOS);

        //return view for patient home
        return "doctor_home";
    }

    //gets doctor image
    @GetMapping("/doctor/image/{id}")
    @ResponseBody
    public ResponseEntity<Resource> getPatientImage(@PathVariable("id") Long id) {
        DoctorDTO doctorDTO = doctorService.findDoctorById(id);

        String profileImagePath = doctorDTO.getProfileImagePath();  // Retrieve the path

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


    //TODO add controls and method for doctor to view patientList


    //TODO add controls and method for doctor to view patientSchedules && patientPrescription

    //TODO add controls and method for doctors to respond to schedules and auto genPrescriptions
}
