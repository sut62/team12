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


    @PostMapping("/pharmacist/{province_id}/{sex_id}/{titlepharmacist_id}/{id_card}/{firstname}/{lastname}/{birthday}/{age}/{address}/{graduated}/{exp}/{phone}/{mail}")
    public Pharmacist newPharmacist(Pharmacist newPharmacist,
    @PathVariable long id_card,
    @PathVariable long province_id,
    @PathVariable long sex_id,
    @PathVariable long titlepharmacist_id,
    @PathVariable String firstname,
    @PathVariable String lastname,
    @PathVariable Date birthday,
    @PathVariable Integer age,
    @PathVariable String address,
    @PathVariable String graduated,
    @PathVariable String exp,
    @PathVariable Integer phone,
    @PathVariable String mail ) {

    Province province = provinceRepository.findById(province_id);
    Sex sex = sexRepository.findById(sex_id);
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(titlepharmacist_id);

    newPharmacist.setProvince(province);
    newPharmacist.setSex(sex);
    newPharmacist.setTitlePharmacist(titlepharmacist);
    newPharmacist.setId_card(id_card);
    newPharmacist.setFirstname(firstname);
    newPharmacist.setLastname(lastname);
    newPharmacist.setBirthday(birthday);
    newPharmacist.setAge(age);  
    newPharmacist.setAddress(address); 
    newPharmacist.setGraduated(graduated);  
    newPharmacist.setExp(exp);  
    newPharmacist.setPhone(phone);  
    newPharmacist.setMail(mail);  

    return pharmacistRepository.save(newPharmacist); //บันทึก Objcet ชื่อ VideoRental
    
    }
}