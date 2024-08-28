package com.s11160663.prototype_v3.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorController {

    @GetMapping("/doctor")
    public String doctor() {
        return "doctor_home";
    }

}
