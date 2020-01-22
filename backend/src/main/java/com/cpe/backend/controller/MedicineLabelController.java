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
import com.cpe.backend.Entity.Unit_of_medicine;
import com.cpe.backend.repository.PrescriptionRepository;
import com.cpe.backend.repository.Unit_of_medicineRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MedicineLabelController {
    @Autowired
    private PrescriptionRepository prescriptionRepository;
   
    @Autowired
    private final MedicineLabelRepository medicineLabelRepository;
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

    @PostMapping("/MedicineLabel/{Prescription_id}/{drug_name}/{effet}/{quantity}/{medicineQuantity_id}/{medicineFrequency_id}/{medicineDuration_id}")
    public MedicineLabel newMedicineLabel(MedicineLabel newMedicineLabel,
    
     @PathVariable long Prescription_id,
     @PathVariable String drug_name,
     @PathVariable String effet, 
     @PathVariable Integer quantity,
     @PathVariable long medicineQuantity_id,
     @PathVariable long medicineFrequency_id,
     @PathVariable long medicineDuration_id)
     

    {
    

    Prescription prescription = prescriptionRepository.findById(Prescription_id);
    MedicineQuantity medicineQuantity = medicineQuantityRepository.findById(medicineQuantity_id);
    MedicineFrequency  medicineFrequency = medicineFrequencyRepository.findById(medicineFrequency_id);
    MedicineDuration medicineDuration = medicineDurationRepository.findById(medicineDuration_id); 
    
    
    
    newMedicineLabel.setPrescription(prescription);
    newMedicineLabel.setDrug_name(drug_name);
    newMedicineLabel.setEffet(effet);
    newMedicineLabel.setQuantity(quantity);
    newMedicineLabel.setMedicineQuantity(medicineQuantity);
    newMedicineLabel.setMedicineFrequency(medicineFrequency);
    newMedicineLabel.setMedicineDuration(medicineDuration);
    
    

    return medicineLabelRepository.save(newMedicineLabel); //บันทึก Objcet ชื่อ VideoRental
    
    }
}