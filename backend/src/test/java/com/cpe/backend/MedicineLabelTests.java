package com.cpe.backend;

import com.cpe.backend.Entity.Prescription;
import com.cpe.backend.Entity.MedicineDuration;
import com.cpe.backend.Entity.MedicineFrequency;
import com.cpe.backend.Entity.MedicineLabel;
import com.cpe.backend.Entity.MedicineQuantity;

import com.cpe.backend.repository.PrescriptionRepository;
import com.cpe.backend.repository.MedicineDurationRepository;
import com.cpe.backend.repository.MedicineFrequencyRepository;
import com.cpe.backend.repository.MedicineLabelRepository;
import com.cpe.backend.repository.MedicineQuantityRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class MedicineLabelTests{

    private Validator validator;
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private MedicineLabelRepository medicineLabelRepository;
    @Autowired
    private MedicineQuantityRepository medicineQuantityRepository;
    @Autowired
    private MedicineFrequencyRepository medicineFrequencyRepository;
    @Autowired
    private MedicineDurationRepository medicineDurationRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        factory.getValidator();
    }
    @Test
    void B5917518_testQuanutityTrue() { // ใส่ข้อมูลปกติ
        MedicineLabel quantitys = new MedicineLabel();
       try{
        quantitys.setQuantity(1);
       }catch(ConstraintViolationException e){
        Set<ConstraintViolation<MedicineLabel>> result = validator.validate(quantitys);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MedicineLabel> v = result.iterator().next();
        assertEquals("must not be false", v.getMessage());
        assertEquals("quantity", v.getPropertyPath().toString());
       }
    }
    @Test
    void B5917518_MedicineLabelSaveOK() {
        
        MedicineLabel medicineLabel = new MedicineLabel();
        MedicineQuantity medicineQuantity = medicineQuantityRepository.findById(4);
        MedicineFrequency medicineFrequency = medicineFrequencyRepository.findById(2);
        MedicineDuration medicineDuration = medicineDurationRepository.findById(1);
        Prescription prescription = prescriptionRepository.findById(120001);
       
        medicineLabel.setPrescription(prescription);
        medicineLabel.setQuantity(1);
        medicineLabel.setMedicineQuantity(medicineQuantity);
        medicineLabel.setMedicineFrequency(medicineFrequency);
        medicineLabel.setMedicineDuration(medicineDuration);
    
        medicineLabel = medicineLabelRepository.saveAndFlush(medicineLabel);

        Optional<MedicineLabel> found = medicineLabelRepository.findById(medicineLabel.getId());
       

        assertEquals(prescription, found.get().getPrescription());
        assertEquals(1, found.get().getQuantity());
        assertEquals(medicineQuantity, found.get().getMedicineQuantity());
        assertEquals(medicineFrequency, found.get().getMedicineFrequency());
        assertEquals(medicineDuration, found.get().getMedicineDuration());
       
    }
  
    @Test
    void B5917518_testQuanutityMustNotNull() { // ใส่ข้อมูลปกติ
        MedicineLabel quantitys = new MedicineLabel();
       try{
        quantitys.setQuantity(null);
       }catch(ConstraintViolationException e){
        Set<ConstraintViolation<MedicineLabel>> result = validator.validate(quantitys);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MedicineLabel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("quantity", v.getPropertyPath().toString());
       }
    }
    
    @Test
    void B5917518_testQuanutityNotZero() { // ใส่ข้อมูลปกติ
        MedicineLabel quantitys = new MedicineLabel();
       try{
        quantitys.setQuantity(0);
       }catch(ConstraintViolationException e){
        Set<ConstraintViolation<MedicineLabel>> result = validator.validate(quantitys);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MedicineLabel> v = result.iterator().next();
        assertEquals("not zero", v.getMessage());
        assertEquals("quantity", v.getPropertyPath().toString());
       }
    }
    @Test
    void B5917518_testQuanutity4() { // ใส่ข้อมูลปกติ
        MedicineLabel quantitys = new MedicineLabel();
       try{
        quantitys.setQuantity(1234);
       }catch(ConstraintViolationException e){
        Set<ConstraintViolation<MedicineLabel>> result = validator.validate(quantitys);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MedicineLabel> v = result.iterator().next();
        assertEquals(" max = 3", v.getMessage());
        assertEquals("quantity", v.getPropertyPath().toString());
       }
    }
}