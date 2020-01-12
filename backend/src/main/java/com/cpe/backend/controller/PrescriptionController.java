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

import com.cpe.backend.Entity.Prescription;
import com.cpe.backend.Entity.Doctor;
import com.cpe.backend.Entity.TitleName;
import com.cpe.backend.Entity.Unit_of_medicine;
import com.cpe.backend.Entity.Drug;
import com.cpe.backend.repository.DrugRepository;
import com.cpe.backend.repository.PrescriptionRepository;
import com.cpe.backend.repository.DoctorRepository;
import com.cpe.backend.repository.TitleNameRepository;
import com.cpe.backend.repository.Unit_of_medicineRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PrescriptionController {
    @Autowired
    private final PrescriptionRepository prescriptionRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private TitleNameRepository titleNameRepository;
    @Autowired
    private Unit_of_medicineRepository unit_of_medicineRepository;
    @Autowired
    private DrugRepository drugRepository;

    PrescriptionController(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @GetMapping("/prescription")
    public Collection<Prescription> Prescriptions() {
        return prescriptionRepository.findAll().stream().collect(Collectors.toList());
    }
    
    @GetMapping("/prescription/{id}")
    public  Optional <Prescription> Prescription(@PathVariable Long id){
            Optional<Prescription> prescription = prescriptionRepository.findById(id);
            return prescription;
    }
    
    @PostMapping("/prescription/{patient_ID}/{TitleName_id}/{name}/{age}/{drugallergy}/{Doctor_id}/{Drug_id}/{amount}/{Unit_of_medicine_id}")
    public Prescription newPrescription(Prescription newPrescription, @PathVariable String patient_ID,
            @PathVariable String name, @PathVariable Integer age, @PathVariable String drugallergy,
            @PathVariable Integer amount, @PathVariable long Drug_id, @PathVariable long TitleName_id,
            @PathVariable long Doctor_id, @PathVariable long Unit_of_medicine_id) {

        TitleName titleName = titleNameRepository.findById(TitleName_id);
        Doctor doctor_name = doctorRepository.findById(Doctor_id);
        Drug drug = drugRepository.findById(Drug_id);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(Unit_of_medicine_id);

        newPrescription.setPatient_ID(patient_ID); // setตามด้วยชื่อตัวแปร
        newPrescription.setTitleName(titleName);
        newPrescription.setName(name);
        newPrescription.setAge(age);
        newPrescription.setDrugallergy(drugallergy);
        newPrescription.setDoctor_name(doctor_name);
        newPrescription.setUnit_of_medicine(unit);
        newPrescription.setDrug(drug);
        newPrescription.setAmount(amount);
        newPrescription.setCreateDate(new Date());

        return prescriptionRepository.save(newPrescription); // บันทึก Objcet ชื่อ Prescription

    }
}