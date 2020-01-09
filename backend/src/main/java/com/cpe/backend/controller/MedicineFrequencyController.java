package com.cpe.backend.controller;


import com.cpe.backend.Entity.MedicineFrequency;
import com.cpe.backend.repository.MedicineFrequencyRepository;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//import java.util.Optional;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MedicineFrequencyController  {

    @Autowired
    private final MedicineFrequencyRepository medicineFrequencyRepository;

    public MedicineFrequencyController (MedicineFrequencyRepository medicineFrequencyRepository) {
        this.medicineFrequencyRepository = medicineFrequencyRepository;
    }
    

    @GetMapping("/frequency")
    public Collection<MedicineFrequency> Frequencys() {
        return medicineFrequencyRepository.findAll().stream().collect(Collectors.toList());
    }

   
}
