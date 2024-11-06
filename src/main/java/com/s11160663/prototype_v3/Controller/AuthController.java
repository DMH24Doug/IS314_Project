package com.s11160663.prototype_v3.Controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import com.s11160663.prototype_v3.DTO.RegistrationDTO;
import com.s11160663.prototype_v3.Model.UserEntity;
import com.s11160663.prototype_v3.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //sets user into homepage when app is launched
    @GetMapping("/")
    public String home(Model model) {

        return "home"; // Replace with the name of your home template
    }

        // Show the login page
        @GetMapping("/login")
        public String login() {
            return "login";  // Renders the login.html page
        }

        // After successful login, check if patient profile exists
        @GetMapping("/login_success")
        public String loginSuccess(Authentication authentication) {
            // Get the logged-in username and log it for debugging
            String username = authentication.getName();
            System.out.println("Logging in user: " + username);

            // Fetch user by username
            UserEntity user = userService.findByUsername(username);

            // Check if the user exists
            if (user == null) {
                System.out.println("User not found for username: " + username);
                return "redirect:/login?error=user_not_found";
            }

            // Check roles of the logged-in user
            boolean isAdmin = user.getRoles().stream()
                    .anyMatch(role -> role.getName().equals("ADMIN"));
            boolean isPatient = user.getRoles().stream()
                    .anyMatch(role -> role.getName().equals("PATIENT"));
            boolean isDoctor = user.getRoles().stream()
                    .anyMatch(role -> role.getName().equals("EMPLOYEE"));

            // Redirect based on roles
            if (isAdmin) {
                System.out.println("Admin user logged in: " + username);
                return "redirect:/admin";
            }

            if (isPatient) {
                // Check if patient profile exists
                if (user.getPatient() == null) {
                    System.out.println("Patient profile not found, redirecting to create profile.");
                    return "redirect:/patient/create";
                } else {
                    System.out.println("Patient profile found, redirecting to patient home.");
                    return "redirect:/patient/patient_home/" + user.getPatient().getId();
                }
            }
            if (isDoctor) {
                // Check if patient profile exists
                if (user.getDoctor() == null) {
                    System.out.println("Doctor profile not found, redirecting to create profile.");
                    return "redirect:/doctor/create";
                } else {
                    System.out.println("Patient profile found, redirecting to patient home.");
                    return "redirect:/doctor/doctor_home/" + user.getDoctor().getId();
                }
            }

            // If no valid role is found, redirect to a generic page
            System.out.println("No valid role found for user: " + username);
            return "redirect:/login?error=invalid_role";
        }

        // Optional: Logout handler
        @GetMapping("/logout")
        public String logout() {
            return "redirect:/login?logout";
        }



    // handler method to User register page request
    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDTO user = new RegistrationDTO();
        model.addAttribute("user", user);
        return "register_patient";
    }
    @PostMapping("/register/save")
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
            return "register_patient";  // Return the register page with error messages
        }

        // Save the new user if no errors
        userService.saveUser(user);
        return "redirect:/login?success";  // Redirect to login page on success
    }



    //updated pages

    //maps to department
    @GetMapping("/department")
    public String department() {return "departments";}

    //maps to about us
    @GetMapping("/about")
    public String about() {return "about";}

    //maps to contact
    @GetMapping("/contact")
    public String contact() {return "contact";}

    //maps to cardiology
    @GetMapping("/cardiology")
    public String cardiology() {return "cardiology";}

    //maps to neurology
    @GetMapping("/neurology")
    public String neurology() {return "neurology";}

    //maps to orthopaedics
    @GetMapping("/orthopaedics")
    public String orthopaedics() {return "orthopaedics";}

    @GetMapping("/scheduling")
    public String scheduling() {return "scheduling";}

}
