package com.cpe.backend.controller;

import com.cpe.backend.Entity.TitlePharmacist;
import com.cpe.backend.repository.TitlePharmacistRepository;

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
public class TitlePharmacistController {

    @Autowired
    private final TitlePharmacistRepository titlepharmacistRepository;

    public TitlePharmacistController(TitlePharmacistRepository titlepharmacistRepository) {
        this.titlepharmacistRepository = titlepharmacistRepository;
    }

    @GetMapping("/titlepharmacist")
    public Collection<TitlePharmacist> Severity_Levels() {
        return titlepharmacistRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/titlepharmacist/{id}")
    public  Optional <TitlePharmacist> TitlePharmacists(@PathVariable Long id){
            Optional<TitlePharmacist> titlepharmacist = titlepharmacistRepository.findById(id);
        return titlepharmacist;
    }
}