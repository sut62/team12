package com.cpe.backend.controller;

import com.cpe.backend.Entity.MedicineQuantity;
import com.cpe.backend.repository.MedicineQuantityRepository;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MedicineQuantityController {

    @Autowired
    private final MedicineQuantityRepository medicineQuantityRepository;
    
    public MedicineQuantityController(MedicineQuantityRepository medicineQuantityRepository) {
        this.medicineQuantityRepository = medicineQuantityRepository;
    }

    @GetMapping("/quantity")
    public Collection<MedicineQuantity> quantitys() {
        return medicineQuantityRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/quantity/{id}")
    public  Optional <MedicineQuantity> quantitys(@PathVariable Long id){
            Optional<MedicineQuantity> medicineQuantity = medicineQuantityRepository.findById(id);
            return medicineQuantity;
    }

}
