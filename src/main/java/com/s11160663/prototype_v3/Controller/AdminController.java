package com.s11160663.prototype_v3.Controller;

import com.s11160663.prototype_v3.DTO.DoctorDTO;
import com.s11160663.prototype_v3.DTO.PatientDTO;
import com.s11160663.prototype_v3.DTO.RegistrationDTO;
import com.s11160663.prototype_v3.Model.UserEntity;
import com.s11160663.prototype_v3.Service.DoctorService;
import com.s11160663.prototype_v3.Service.PatientService;
import com.s11160663.prototype_v3.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    private PatientService patientService;
    private UserService userService;
    private DoctorService doctorService;


    @Autowired
    public AdminController(PatientService patientService, UserService userService, DoctorService doctorService) {
        this.patientService = patientService;
        this.userService = userService;
        this.doctorService = doctorService;
    }
    @GetMapping("/admin")
    public String admin() {

        return "admin_home";
    }

    @GetMapping("admin/view_patientList")
    public String viewPatientList(@NotNull Model model) {
        List<PatientDTO> patientDTOS = patientService.findAllPatients();
        model.addAttribute("patients", patientDTOS);
        return "admin_view_patients";
    }
    @GetMapping("admin/view_doctorList")
    public String viewDoctorList(@NotNull Model model) {
        List<DoctorDTO> doctorDTOS = doctorService.findAllDoctors();
        model.addAttribute("doctors", doctorDTOS);
        return "admin_view_doctors";
    }

    @GetMapping("admin/register_doctor")
    public String register(Model model) {
        //creates new user (if registration is successful)
        RegistrationDTO user = new RegistrationDTO();
        model.addAttribute("user", user);
        return "register";

    }

    //post methods for registration page
    @PostMapping("admin/register_doctor/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDTO user,
                           BindingResult result, Model model) {
        //finds user by email (check if user already exist or not)
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        //if email matches existing one app will return (invalid)
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }
        //finds user by email (check if user already exist or not)
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if(existingUserUsername != null && existingUserUsername.getName() != null && !existingUserUsername.getName().isEmpty()) {
            return "redirect:/register?fail";
        }
        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveDoctor(user);
        return "redirect:/admin_home?success";
    }

    //TODO add controls and method for admin to add doctor
}
