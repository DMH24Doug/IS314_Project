package com.s11160663.prototype_v3.Controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;


@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //sets user into homepage when app is launched
    @GetMapping("/")
    public String home() {
        return "home";
    }

    //user login page
    @GetMapping("/login")
    public String login(Authentication authentication) {
//        if (authentication != null && authentication.isAuthenticated()) {
//            String username = authentication.getName();
//
//            //retrieving user details to get userID
//            UserEntity user = (UserEntity) authentication.getPrincipal();
//            Long userId = user.getId();
//
//            //gets user respectively by Roles and IDs to their home page
//            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//            for (GrantedAuthority authority : authorities) {
//                String role = authority.getAuthority();
//                if (role.equals("ADMIN")) {
//                    return "redirect:/admin" + userId;
//                }else if (role.equals("EMPLOYEE")) {
//                    return "redirect:/employee" + userId;
//                }else if (role.equals("PATIENT")) {
//                    return "redirect:/patient" + userId;
//                }
//            }
//        }


        return "login";
    }

//    //user registration page
    @GetMapping("/register")
    public String register(Model model) {
        //creates new user (if registration is successful)
        RegistrationDTO user = new RegistrationDTO();
        model.addAttribute("user", user);
        return "register";

    }

    //post methods for registration page
    @PostMapping("/register/save")
    public String register(
            @Valid @ModelAttribute("user") RegistrationDTO user,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            redirectAttributes.addFlashAttribute("user", user); // Preserve input
            return "redirect:/register";
        }

        // Check if email or username already exists
        if (userService.findByEmail(user.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("message", "Email already in use.");
            return "redirect:/register";
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            redirectAttributes.addFlashAttribute("message", "Username already in use.");
            return "redirect:/register";
        }

        userService.saveUser(user);
        return "redirect:/patient/create?success";
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

}
