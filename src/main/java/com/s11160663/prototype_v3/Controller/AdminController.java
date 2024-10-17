package com.s11160663.prototype_v3.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin() {

        return "admin_home";
    }

    //TODO add controls and method for admin to add doctor
}
