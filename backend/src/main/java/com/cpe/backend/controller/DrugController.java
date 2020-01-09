package com.cpe.backend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Collection;
//import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;


import com.cpe.backend.Entity.Drug;
import com.cpe.backend.Entity.Unit_of_medicine;
import com.cpe.backend.Entity.DrugCategory;
import com.cpe.backend.Entity.Pharmacist;
import com.cpe.backend.repository.Unit_of_medicineRepository;
import com.cpe.backend.repository.DrugRepository;
import com.cpe.backend.repository.DrugCategoryRepository;
import com.cpe.backend.repository.PharmacistRepository;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DrugController {
    @Autowired
    private final DrugRepository drugRepository;
    @Autowired
    private Unit_of_medicineRepository unit_of_medicineRepository;
    @Autowired
    private DrugCategoryRepository drugcategoryRepository;
    @Autowired
    private PharmacistRepository pharmacistRepository;
   

    DrugController(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @GetMapping("/drug")
    public Collection<Drug> Drug() {
        return drugRepository.findAll().stream().collect(Collectors.toList());
    }

    

    @PostMapping("/drug/{drugcategory_id}/{Unit_of_medicine_id}/{how}/{sideeffect}/{price}/{pharmacist_id}")
    public Drug newDrug(Drug newDrug,
    @PathVariable long drugcategory_id,
    @PathVariable long Unit_of_medicine_id,
    @PathVariable String how,
    @PathVariable String sideeffect,
    @PathVariable long price,
    @PathVariable long pharmacist_id) {


    DrugCategory drugcategory = drugcategoryRepository.findById(drugcategory_id);
    Unit_of_medicine unit = unit_of_medicineRepository.findById(Unit_of_medicine_id);
    Pharmacist pharmacist = pharmacistRepository.findById(pharmacist_id);

    newDrug.setDrugcategory(drugcategory);
    newDrug.setUnit_of_medicine(unit);
    newDrug.setHow(how);
    newDrug.setSideeffect(sideeffect);
    newDrug.setPrice(price);  
    newDrug.setPharmacist(pharmacist); 

    return drugRepository.save(newDrug); 
    
    }
}