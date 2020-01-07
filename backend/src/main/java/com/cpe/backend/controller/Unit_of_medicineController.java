package com.cpe.backend.controller;

import com.cpe.backend.Entity.Unit_of_medicine;
import com.cpe.backend.repository.Unit_of_medicineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class Unit_of_medicineController {

    @Autowired
    private final Unit_of_medicineRepository unit_of_medicineRepository;

    public Unit_of_medicineController(Unit_of_medicineRepository unit_of_medicineRepository) {
        this.unit_of_medicineRepository = unit_of_medicineRepository;
    }

    @GetMapping("/unit_of_medicine")
    public Collection<Unit_of_medicine> Unit_of_medicines() {
        return unit_of_medicineRepository.findAll().stream().collect(Collectors.toList());
    }

}