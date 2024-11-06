package com.s11160663.prototype_v3.Controller;

import com.s11160663.prototype_v3.DTO.MedicationDTO;
import com.s11160663.prototype_v3.Repository.MedicationRepository;
import com.s11160663.prototype_v3.Service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MedicationController {

    private MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medService) {
        this.medicationService = medService;
    }

    //todo fix controls
    @GetMapping("/details")
    public ResponseEntity<MedicationDTO> getMedicationDetails(@RequestParam String name) {
        MedicationDTO medication = medicationService.getMedicationByName(name);
        if (medication != null) {
            return ResponseEntity.ok(medication);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
