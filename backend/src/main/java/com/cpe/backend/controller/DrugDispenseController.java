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
import com.cpe.backend.repository.DrugDispenseRepository;
import com.cpe.backend.repository.DrugDispenseChannelRepository;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DrugDispenseController {
    @Autowired
    private final DrugDispenseRepository drugDispenseRepository;
    @Autowired
    private DrugDispenseChannelRepository drugDispenseChannelRepository;
   
    DrugDispenseController(DrugDispenseRepository drugDispenseRepository) {
        this.drugDispenseRepository = drugDispenseRepository;
    }

    @GetMapping("/drugdispense")
    public Collection<DrugDispense> drugDispenses() {
        return drugDispenseRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/recordBill/{DrugDispenseChannel_id}")
    public DrugDispense newDrugDispense(DrugDispense newDrugDispense, 
            @PathVariable long DrugDispenseChannel_id) {

        DrugDispenseChannel drugdispensechannel = drugDispenseChannelRepository.findById(DrugDispenseChannel_id);

        newDrugDispense.setDrugdispensechannel(drugdispensechannel);

        return drugDispenseRepository.save(newDrugDispense); // บันทึก Objcet ชื่อ RecordBill

    }
}