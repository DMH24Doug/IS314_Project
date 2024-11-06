package com.s11160663.prototype_v3.Controller;

import com.s11160663.prototype_v3.DTO.DoctorDTO;
import com.s11160663.prototype_v3.DTO.PatientDTO;
import com.s11160663.prototype_v3.DTO.RegistrationDTO;
import com.s11160663.prototype_v3.Model.UserEntity;
import com.s11160663.prototype_v3.Service.DoctorService;
import com.s11160663.prototype_v3.Service.PatientService;
import com.s11160663.prototype_v3.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {


    private final UserService userService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public AdminController(UserService userService, PatientService patientService, DoctorService doctorService) {
        this.userService = userService;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }
    @GetMapping("/admin")
    public String admin() {

        return "admin_home";
    }

    //TODO add controls and method for admin to add doctor
    // handler method to User register page request
    @GetMapping("/admin/register_doctor")
    public String getRegisterForm(Model model) {
        RegistrationDTO user = new RegistrationDTO();
        model.addAttribute("user", user);
        return "register_doctor";
    }

    @PostMapping("/admin/register_doctor/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDTO user,
                           BindingResult result, Model model) {

        // Check if the email already exists
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null) {
            result.rejectValue("email", "error.user", "Email already in use.");
        }

        // Check if the username already exists
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if (existingUserUsername != null && existingUserUsername.getName() != null) {
            result.rejectValue("username", "error.user", "Username already in use.");
        }

        // If there are validation errors, return to the registration form
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register_doctor";  // Return the register page with error messages
        }

        // Save the new user if no errors
        userService.saveDoctor(user);
        return "redirect:/admin_home?success";  // Redirect to login page on success
    }


    //view patient list
    @GetMapping("/admin/patientList")
    public String patientList(Model model) {
        List<PatientDTO> patientDTO = patientService.findAllPatients();
        model.addAttribute("patient", patientDTO);
        return "admin_view_patients";
    }

    //view doctor list
    @GetMapping("/admin/doctorList")
    public String doctorList(Model model) {
        List<DoctorDTO> doctorDTO = doctorService.findAllDoctors();
        model.addAttribute("doctor", doctorDTO);
        return "admin_view_doctors";
    }
}
