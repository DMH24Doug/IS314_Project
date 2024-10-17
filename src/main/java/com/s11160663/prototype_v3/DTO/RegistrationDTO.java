package com.s11160663.prototype_v3.DTO;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationDTO {

    private Long id;
    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric.")
    private String username;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least 8 characters, one uppercase, one lowercase, one number, and one special character."
    )
    private String password;
}
