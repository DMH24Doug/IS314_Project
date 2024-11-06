package com.s11160663.prototype_v3.Controller;

import com.s11160663.prototype_v3.Model.DoctorEntity;
import com.s11160663.prototype_v3.Model.PatientEntity;
import com.s11160663.prototype_v3.Model.UserEntity;
import com.s11160663.prototype_v3.Service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

    private final UserService userService;

    public GlobalController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void verifyUser(Model model) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user is logged in
        if (authentication != null && authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser")) {
            // Get the username of the logged-in user
            String username = authentication.getName();

            // Fetch the user entity by username
            UserEntity user = userService.findByUsername(username);
            // Handle the different user
            if (user.getPatient() != null) {
                PatientEntity patient = user.getPatient();
                model.addAttribute("id", patient.getId());
                model.addAttribute("image", patient.getProfileImagePath());
                model.addAttribute("role", "PATIENT");
            } else if (user.getDoctor() != null) {
                DoctorEntity doctor = user.getDoctor();
                model.addAttribute("id", doctor.getId());
                model.addAttribute("image", doctor.getProfileImagePath());
                model.addAttribute("role", "EMPLOYEE");
            } else {
                // Assume it's an admin if no patient or doctor is found
                model.addAttribute("id", user.getId());  // Admin's user ID
                model.addAttribute("image", "/image/admin-profile.png");  // Default admin image
                model.addAttribute("role", "ADMIN");

            }
        }
    }
}
