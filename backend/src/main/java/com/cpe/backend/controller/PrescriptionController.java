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

    PrescriptionController(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @GetMapping("/prescription")
    public Collection<Prescription> Prescriptions() {
        return prescriptionRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/prescription/{patient_ID}/{Doctor_id}/{TitleName_id}/{Unit_of_medicine_id}")
    public Prescription newPrescription(Prescription newPrescription, @PathVariable String patient_ID,
            @PathVariable String name, @PathVariable Integer age, @PathVariable String drugallergy,
            @PathVariable long TitleName_id, @PathVariable long Doctor_id, @PathVariable long Unit_of_medicine_id) {

        Doctor doctor_name = doctorRepository.findById(Doctor_id);
        TitleName titleName = titleNameRepository.findById(TitleName_id);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(Unit_of_medicine_id);

        newPrescription.setDoctor_name(doctor_name);
        newPrescription.setTitleName(titleName);
        newPrescription.setUnit_of_medicine(unit);
        newPrescription.setPatient_ID(patient_ID); // setตามด้วยชื่อตัวแปร
        newPrescription.setName(name);
        newPrescription.setAge(age);
        newPrescription.setDrugallergy(drugallergy);
        newPrescription.setCreateDate(new Date());

        return prescriptionRepository.save(newPrescription); // บันทึก Objcet ชื่อ Prescription

    }
}