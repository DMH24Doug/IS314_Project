package com.s11160663.prototype_v3.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin() {

        return "admin_home";
    }

    //TODO add controls and method for admin to add doctor
}
