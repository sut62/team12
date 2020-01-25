package com.cpe.backend.controller;

import com.cpe.backend.Entity.Sex;
import com.cpe.backend.repository.SexRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class SexController {

    @Autowired
    private final SexRepository sexRepository;

    public SexController(SexRepository sexRepository) {
        this.sexRepository = sexRepository;
    }

    @GetMapping("/sex")
    public Collection<Sex> Severity_Levels() {
        return sexRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/sex/{id}")
    public  Optional <Sex> Sex(@PathVariable Long id){
            Optional<Sex> sex = sexRepository.findById(id);
        return sex;
    }
}