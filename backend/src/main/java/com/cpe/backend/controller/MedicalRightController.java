package com.cpe.backend.controller;

import com.cpe.backend.Entity.MedicalRight;
import com.cpe.backend.repository.MedicalRightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MedicalRightController {

    @Autowired
    private final MedicalRightRepository medicalRightRepository;

    public MedicalRightController(MedicalRightRepository medicalRightRepository) {
        this.medicalRightRepository = medicalRightRepository;
    }

    @GetMapping("/medicalRight")
    public Collection<MedicalRight> medicalRights() {
        return medicalRightRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/medicalRight/{id}")
    public Optional<MedicalRight> MedicalRights(@PathVariable Long id) {
        Optional<MedicalRight> medicalright = medicalRightRepository.findById(id);
        return medicalright;
    }

}