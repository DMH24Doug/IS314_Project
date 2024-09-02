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
    public String register(@Valid @ModelAttribute("user") RegistrationDTO user,
                           BindingResult result, RedirectAttributes redirectAttributes) {
        // Find user by email (check if user already exists)
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Email already in use.");
            return "redirect:/register?fail";
        }

        // Find user by username (check if user already exists)
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if(existingUserUsername != null && existingUserUsername.getName() != null && !existingUserUsername.getName().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Username already in use.");
            return "redirect:/register?fail";
        }

        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/register";
        }

        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("success", "Registration successful!");
        return "redirect:/patient/create?success";
    }

}
