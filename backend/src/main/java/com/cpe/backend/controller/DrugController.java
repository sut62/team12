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


import com.cpe.backend.entity.Drug;
import com.cpe.backend.entity.DrugType;
import com.cpe.backend.entity.DrugCategory;
import com.cpe.backend.repository.DrugRepository;
import com.cpe.backend.repository.DrugTypeRepository;
import com.cpe.backend.repository.DrugCategoryRepository;


//เอามาจากของเพื่อน
//import com.cpe.backend.b5903900_register.entity.Patient;
//import com.cpe.backend.b5903900_register.repository.PatientRepository;  


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DrugController {
    @Autowired
    private final DrugRepository drugRepository;
    @Autowired
    private DrugTypeRepository drugtypeRepository;
    @Autowired
    private DrugCategoryRepository drugcategoryRepository;
   

    DrugController(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @GetMapping("/drug")
    public Collection<Drug> Drug() {
        return drugRepository.findAll().stream().collect(Collectors.toList());
    }

    

    @PostMapping("/drug/{drugcategory_id}/{drugtype_id}/{how}/{sideeffect}/{price}/{pharmacist_id}")
    public Drug newDrug(Drug newDrug,
    @PathVariable long drugcategory_id,
    @PathVariable long drugtype_id,
    @PathVariable String how,
    @PathVariable String sideeffect,
    @PathVariable long price,
    @PathVariable long pharmacist_id) {


    DrugCategory drugcategory = drugcategoryRepository.findById(drugcategory_id);
    DrugType drugtype = drugtypeRepository.findById(drugtype_id);

    //--
    DoctorProfile createdBy = doctorProfileR.findById(doctorprofile_id);

    newAppointments.setAppmPatient(appmPatient);
    newAppointments.setCreatedBy(createdBy);
    newAppointments.setDuration(duration);
    newAppointments.setAppmDate(appmDate);
    newAppointments.setRoom(room);  
    newAppointments.setDetail(detail); 

    return appointmentsRepository.save(newAppointments); //บันทึก Objcet ชื่อ VideoRental
    
    }
}