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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;


import com.cpe.backend.Entity.Pharmacist;
import com.cpe.backend.Entity.TitlePharmacist;
import com.cpe.backend.Entity.Province;
import com.cpe.backend.Entity.Sex;
import com.cpe.backend.repository.PharmacistRepository;
import com.cpe.backend.repository.TitlePharmacistRepository;
import com.cpe.backend.repository.ProvinceRepository;
import com.cpe.backend.repository.SexRepository;
  

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PharmacistController {
    @Autowired
    private final PharmacistRepository pharmacistRepository;
    @Autowired
    private  TitlePharmacistRepository titlepharmacistRepository;
    @Autowired
    private SexRepository sexRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    
   

    PharmacistController(PharmacistRepository pharmacistRepository) {
        this.pharmacistRepository = pharmacistRepository;
    }

    @GetMapping("/pharmacist")
    public Collection<Pharmacist> Pharmacist() {
        return pharmacistRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/pharmacist/{id}")
    public  Optional <Pharmacist> Pharmacists(@PathVariable Long id){
            Optional<Pharmacist> pharmacist = pharmacistRepository.findById(id);
            return pharmacist;
    }

    @PostMapping("/pharmacist/{id_card}/{titlepharmacist_id}/{name}/{sex_id}/{age}/{province_id}/{address}/{phone}/{mail}")
    public Pharmacist newPharmacist(Pharmacist newPharmacist,
    @PathVariable String id_card,
    @PathVariable long province_id,
    @PathVariable long sex_id,
    @PathVariable long titlepharmacist_id,
    @PathVariable String name,
    @PathVariable Integer age,
    @PathVariable String address,
    @PathVariable String phone,
    @PathVariable String mail ) {

    Province province = provinceRepository.findById(province_id);
    Sex sex = sexRepository.findById(sex_id);
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(titlepharmacist_id);

    newPharmacist.setProvince(province);
    newPharmacist.setSex(sex);
    newPharmacist.setTitlePharmacist(titlepharmacist);
    newPharmacist.setId_card(id_card);
    newPharmacist.setName(name);
    newPharmacist.setAge(age);  
    newPharmacist.setAddress(address); 
    newPharmacist.setPhone(phone);  
    newPharmacist.setMail(mail);  

    return pharmacistRepository.save(newPharmacist); //บันทึก Objcet ชื่อ VideoRental
    
    }
}