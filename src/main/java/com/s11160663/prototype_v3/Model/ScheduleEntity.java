package com.s11160663.prototype_v3.Model;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleEntity {

    @GetMapping("/schedule")
    public String schedule(Model model) {
        return "schedule";
    }
}
