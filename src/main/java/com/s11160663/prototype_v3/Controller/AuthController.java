package com.s11160663.prototype_v3.Controller;


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
    public String home() {
        return "home";
    }

    //user login page
    @GetMapping("/login")
    public String login(Model model) {
//        if (authentication != null && authentication.isAuthenticated()) {
//            //String username = authentication.getName();
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
//                    return "redirect:/admin/" + userId;
//                }else if (role.equals("EMPLOYEE")) {
//                    return "redirect:/employee/" + userId;
//                }else if (role.equals("PATIENT")) {
//                    return "redirect:/patient/" + userId;
//                }
//            }
//        }

        RegistrationDTO user = new RegistrationDTO();
        model.addAttribute("user", user);
        return "login2";
    }

//    //user registration page
//    @GetMapping("/register")
//    public String register(Model model) {
//        //creates new user (if registration is successful)
//
//        return "sign_in";
//
//    }

    //post methods for registration page
    @PostMapping("/login/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDTO user,
                           BindingResult result, Model model) {
        //finds user by email (check if user already exist or not)
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        //if email matches existing one app will return (invalid)
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/login?fail";
        }
        //finds user by email (check if user already exist or not)
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if(existingUserUsername != null && existingUserUsername.getName() != null && !existingUserUsername.getName().isEmpty()) {
            return "redirect:/login?fail";
        }
        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "login2";
        }
        userService.saveUser(user);
        return "redirect:/patient/create?success";
    }
}
