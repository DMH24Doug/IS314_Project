package com.s11160663.prototype_v3.Security;

import com.s11160663.prototype_v3.Model.UserEntity;
import com.s11160663.prototype_v3.Repository.UserRepository;
import com.s11160663.prototype_v3.Service.PatientService;
import com.s11160663.prototype_v3.Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {


    private UserService userService;
    @Autowired
    public CustomAuthSuccessHandler(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Get the username of the logged-in user
        String username = authentication.getName();
        System.out.println("User logged in: " + username);

        // Fetch the user entity from the database
        UserEntity user = userService.findByUsername(username);

        if (user == null) {
            System.out.println("User not found: " + username);
            response.sendRedirect("/login?error=user_not_found");
            return;
        }

        // Get the roles of the authenticated user
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Check if the user has the PATIENT role
        boolean isPatient = authorities.stream()
                .anyMatch(role -> role.getAuthority().equals("PATIENT"));

        if (isPatient) {
            // Check if the patient profile exists
            if (user.getPatient() == null) {
                System.out.println("Redirecting to create patient profile for: " + username);
                response.sendRedirect("/patient/create");
            } else {
                System.out.println("Redirecting to patient home for: " + username);
                response.sendRedirect("/patient/patient_home/" + user.getPatient().getId());
            }
        } else if (authorities.stream().anyMatch(role -> role.getAuthority().equals("ADMIN"))) {
            System.out.println("Redirecting to admin dashboard for: " + username);
            response.sendRedirect("/admin/dashboard");
        } else if (authorities.stream().anyMatch(role -> role.getAuthority().equals("EMPLOYEE"))) {
            System.out.println("Redirecting to employee home for: " + username);
            response.sendRedirect("/doctor/doctor_home" + user.getDoctor().getId());
        } else {
            // Fallback if the user has no valid roles
            System.out.println("No valid role found for user: " + username);
            response.sendRedirect("/login?error=invalid_role");
        }
    }
}
