package com.s11160663.prototype_v3.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegistrationDTO {

    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
