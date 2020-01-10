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

import com.cpe.backend.Entity.DrugDispense;
import com.cpe.backend.Entity.DrugDispenseChannel;
import com.cpe.backend.Entity.Pharmacist;
import com.cpe.backend.Entity.TitlePharmacist;
import com.cpe.backend.Entity.Prescription;

import com.cpe.backend.repository.DrugDispenseRepository;
import com.cpe.backend.repository.DrugDispenseChannelRepository;
import com.cpe.backend.repository.PharmacistRepository;
import com.cpe.backend.repository.TitlePharmacistRepository;
import com.cpe.backend.repository.PrescriptionRepository;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DrugDispenseController {
    @Autowired
    private DrugDispenseRepository drugDispenseRepository;
    @Autowired
    private DrugDispenseChannelRepository drugDispenseChannelRepository;
    @Autowired
    private PharmacistRepository pharmacistRepository;
    @Autowired
    private TitlePharmacistRepository titlepharmacistRepository;
    @Autowired
    private PrescriptionRepository  prescriptionRepository;

    DrugDispenseController(DrugDispenseRepository drugDispenseRepository) {
        this.drugDispenseRepository = drugDispenseRepository;
    }

    @GetMapping("/drugdispense")
    public Collection<DrugDispense> drugDispenses() {
        return drugDispenseRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/drugdispense/{DrugDispenseChannel_id}/{titlepharmacist_id}/{pharmacist_id}/{prescription_id}")
    public DrugDispense newDrugDispense(DrugDispense newDrugDispense, 
            @PathVariable long DrugDispenseChannel_id,
            @PathVariable long titlepharmacist_id,
            @PathVariable long pharmacist_id,
            @PathVariable long prescription_id
            ) {

        DrugDispenseChannel drugdispensechannel = drugDispenseChannelRepository.findById(DrugDispenseChannel_id);
        TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(titlepharmacist_id);
        Pharmacist pharmacist = pharmacistRepository.findById(pharmacist_id);
        Prescription prescription = prescriptionRepository.findById(prescription_id);

        newDrugDispense.setDrugdispensechannel(drugdispensechannel);
        newDrugDispense.setTitlePharmacist(titlepharmacist);
        newDrugDispense.setPharmacist(pharmacist);
        newDrugDispense.setPrescription(prescription);

        return drugDispenseRepository.save(newDrugDispense); // บันทึก Objcet ชื่อ DrugDispense

    }
}