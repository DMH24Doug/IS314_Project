package com.s11160663.prototype_v3.Initializer;


import com.s11160663.prototype_v3.Model.MedicationEntity;
import com.s11160663.prototype_v3.Model.Roles;
import com.s11160663.prototype_v3.Model.UserEntity;
import com.s11160663.prototype_v3.Repository.MedicationRepository;
import com.s11160663.prototype_v3.Repository.UserRepository;
import com.s11160663.prototype_v3.Service.MedicationService;
import com.s11160663.prototype_v3.Service.RolesService;
import com.s11160663.prototype_v3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RolesService rolesService;
    private UserService userService;
    private MedicationService medicationService;
    @Autowired
    private UserRepository userRepository;
    private MedicationRepository medicationRepository;

    public DataInitializer(MedicationService medicationService) {
        this.medicationService = medicationService;
    }


    @Override
    public void run(String... args) throws Exception {

        //creates roles in database on run

        Roles admin = rolesService.createRoleIfNotExists("ADMIN");
        Roles employee = rolesService.createRoleIfNotExists("EMPLOYEE");
        Roles patient = rolesService.createRoleIfNotExists("PATIENT");

        MedicationEntity amoxicillin = medicationService.createMedicationIfNotExists("Amoxicillin");
        MedicationEntity ibuprofen = medicationService.createMedicationIfNotExists("Ibuprofen");
        MedicationEntity paracetamol = medicationService.createMedicationIfNotExists("Paracetamol");
        MedicationEntity aspirin = medicationService.createMedicationIfNotExists("Aspirin");
        MedicationEntity metformin = medicationService.createMedicationIfNotExists("Metformin");
        MedicationEntity lisinopril = medicationService.createMedicationIfNotExists("Lisinopril");
        MedicationEntity atorvastatin = medicationService.createMedicationIfNotExists("Atorvastatin");
        MedicationEntity albuterol = medicationService.createMedicationIfNotExists("Albuterol");


    }



}
