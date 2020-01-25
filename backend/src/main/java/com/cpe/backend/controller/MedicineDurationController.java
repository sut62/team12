package com.cpe.backend.controller;

import com.cpe.backend.Entity.MedicineDuration;
import com.cpe.backend.repository.MedicineDurationRepository;
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
public class MedicineDurationController {

    @Autowired
    private final MedicineDurationRepository medicineDurationRepository;
    
    public MedicineDurationController(MedicineDurationRepository medicineDurationRepository) {
        this.medicineDurationRepository = medicineDurationRepository;
    }

    @GetMapping("/duration")
    public Collection<MedicineDuration> durations() {
        return medicineDurationRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/duration/{id}")
    public  Optional <MedicineDuration> quantitys(@PathVariable Long id){
            Optional<MedicineDuration> medicineDuration = medicineDurationRepository.findById(id);
            return medicineDuration;
    }

}
