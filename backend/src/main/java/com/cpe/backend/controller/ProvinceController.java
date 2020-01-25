package com.cpe.backend.controller;

import com.cpe.backend.Entity.Province;
import com.cpe.backend.repository.ProvinceRepository;

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
public class ProvinceController {

    @Autowired
    private final ProvinceRepository provinceRepository;

    public ProvinceController(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @GetMapping("/province")
    public Collection<Province> Severity_Levels() {
        return provinceRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/province/{id}")
    public  Optional <Province> Province(@PathVariable Long id){
            Optional<Province> province = provinceRepository.findById(id);
        return province;
    }
}