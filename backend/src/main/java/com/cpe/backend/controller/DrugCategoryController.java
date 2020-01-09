package com.cpe.backend.controller;

import com.cpe.backend.Entity.DrugCategory;
import com.cpe.backend.repository.DrugCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DrugCategoryController {

    @Autowired
    private DrugCategoryRepository drugcategoryRepository;

    public DrugCategoryController(DrugCategoryRepository drugcategoryRepository) {
        this.drugcategoryRepository = drugcategoryRepository;
    }

    @GetMapping("/drugcategory")
    public Collection<DrugCategory> DrugCategorys() {
        return drugcategoryRepository.findAll().stream().collect(Collectors.toList());
    }

}