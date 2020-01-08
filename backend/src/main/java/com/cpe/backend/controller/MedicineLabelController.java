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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.cpe.backend.Entity.MedicineDuration;
import com.cpe.backend.Entity.MedicineFrequency;
import com.cpe.backend.Entity.MedicineLabel;
import com.cpe.backend.Entity.MedicineQuantity;

import com.cpe.backend.repository.MedicineDurationRepository;
import com.cpe.backend.repository.MedicineFrequencyRepository;
import com.cpe.backend.repository.MedicineLabelRepository;
import com.cpe.backend.repository.MedicineQuantityRepository;

//ใช้ร่วมกับเพื่อน
import com.cpe.backend.Entity.Prescription;
import com.cpe.backend.repository.PrescriptionRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MedicineLabelController {
    @Autowired
    private final MedicineLabelRepository medicineLabelRepository;
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private MedicineDurationRepository medicineDurationRepository;
    @Autowired
    private MedicineFrequencyRepository medicineFrequencyRepository;
    @Autowired
    private MedicineQuantityRepository medicineQuantityRepository;


    MedicineLabelController(MedicineLabelRepository medicineLabelRepository) { //name controller:name reoository
        this.medicineLabelRepository = medicineLabelRepository;
    }

    @GetMapping("/MedicineLabel")
    public Collection<MedicineLabel> MedicineLabels() {
        return medicineLabelRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/MedicineLabel/{prescription_id}/{quantity_id}/Frequency_id}/{duration_id}")
    public MedicineLabel newSchedule(MedicineLabel newMedicineLabel,
    @PathVariable long prescription_id,
    @PathVariable long quantity_id,
    @PathVariable long Frequency_id,
    @PathVariable long duration_id){
    

    Prescription prescription = prescriptionRepository.findById(prescription_id);
    MedicineQuantity periodTime = medicineQuantityRepository.findById(quantity_id);
    MedicineFrequency  Frequency = medicineFrequencyRepository.findById(Frequency_id);
    MedicineDuration duration = medicineDurationRepository.findById(duration_id); 
    
    
    
    newMedicineLabel.getPrescription();
    newMedicineLabel.setPrescription(prescription);
    newMedicineLabel.setQuantity(quantity);
    newMedicineLabel.setFrequency(Frequency);
    newMedicineLabel.setDuration(duration);
    
    

    return medicineLabelRepository.save(newMedicineLabel); //บันทึก Objcet ชื่อ VideoRental
    
    }
}