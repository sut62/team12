package com.cpe.backend;

import com.cpe.backend.Entity.Drug;
import com.cpe.backend.Entity.Unit_of_medicine;
import com.cpe.backend.Entity.DrugCategory;
import com.cpe.backend.Entity.Pharmacist;

import com.cpe.backend.repository.Unit_of_medicineRepository;
import com.cpe.backend.repository.DrugRepository;
import com.cpe.backend.repository.DrugCategoryRepository;
import com.cpe.backend.repository.PharmacistRepository;

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
import javax.validation.constraints.NotNull;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



@DataJpaTest
public class DrugTest {

    private Validator validator;

    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private Unit_of_medicineRepository unit_of_medicineRepository;
    @Autowired
    private DrugCategoryRepository drugcategoryRepository;
    @Autowired
    private PharmacistRepository pharmacistRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        factory.getValidator();
    }

    // DrugName กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5903832_testDrugNamewithCorrect() {
        Drug drug = new Drug();
        Unit_of_medicine unit_of_medicine = unit_of_medicineRepository.findById(2);
        DrugCategory category = drugcategoryRepository.findById(2);
        Pharmacist pharmacist = pharmacistRepository.findById(1);

        drug.setDrugname("paracetamol");
        drug.setCategory(category);
        drug.setUnit_of_medicine(unit_of_medicine);
        drug.setHow("eat 2 times per day");
        drug.setSideEffect("dont");
        drug.setPrice(20);  
        drug.setPharmacist(pharmacist); 

        drug = drugRepository.saveAndFlush(drug);
 
        Optional<Drug> found = drugRepository.findById(drug.getID());
        assertEquals("paracetamol", found.get().getDrugname());
        assertEquals(category, found.get().getCategory());
        assertEquals(unit_of_medicine, found.get().getUnit_of_medicine());
        assertEquals("eat 2 times per day", found.get().getHow());
        assertEquals("dont", found.get().getSideEffect());
        assertEquals(20, found.get().getPrice());
        assertEquals(pharmacist, found.get().getPharmacist());
    }


        // DrugName กรณีที่ 2 น้อยกว่า 3 ตัว
        @Test
        void B5903832_testDrugNameSizelessthan3() {
            Drug drug = new Drug();
        try {
            drug.setDrugname("k");
        } catch (ConstraintViolationException e) {
    
            Set<ConstraintViolation<Drug>> result = validator.validate(drug);
    
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Drug> message = result.iterator().next();
            assertEquals("size must be between 3 and 50", message.getMessage());
            assertEquals("Drugname", message.getPropertyPath().toString());
        }
        }
    
        // DrugName กรณีที่ 3 มากกว่า 30 ตัว
        @Test
        void B5903832_testDrugNameSizeMorethan30() {
            Drug drug = new Drug();
        try {
            drug.setDrugname("ParaHParaHParaHParaHParaHParaHParaHParaHParaHParaHParaHParaHParaH");
        } catch (ConstraintViolationException e) {
    
            Set<ConstraintViolation<Drug>> result = validator.validate(drug);
    
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Drug> message = result.iterator().next();
            assertEquals("size must be between 3 and 50", message.getMessage());
            assertEquals("Drugname", message.getPropertyPath().toString());
            }
        }
    
        // DrugName กรณีที่ 4 ชื่อต้องไม่ว่างเปล่า
        @Test
        void B5903832_testDrugMustNotBeNull() {
            Drug drug = new Drug();
        try {
            drug.setDrugname(null);
        } catch (ConstraintViolationException e) {
    
            Set<ConstraintViolation<Drug>> result = validator.validate(drug);
    
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Drug> message = result.iterator().next();
            assertEquals("drugname must not be null", message.getMessage());
            assertEquals("Drugname", message.getPropertyPath().toString());
            }
        }

       // HowName กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5903832_testHowwithCorrect() {
        Drug drug = new Drug();
        Unit_of_medicine unit_of_medicine = unit_of_medicineRepository.findById(2);
        DrugCategory category = drugcategoryRepository.findById(2);
        Pharmacist pharmacist = pharmacistRepository.findById(1);

        drug.setDrugname("paracetamol");
        drug.setCategory(category);
        drug.setUnit_of_medicine(unit_of_medicine);
        drug.setHow("eat 2 times per day");
        drug.setSideEffect("dont");
        drug.setPrice(20);  
        drug.setPharmacist(pharmacist); 

        drug = drugRepository.saveAndFlush(drug);
 
        Optional<Drug> found = drugRepository.findById(drug.getID());
        assertEquals("paracetamol", found.get().getDrugname());
        assertEquals(category, found.get().getCategory());
        assertEquals(unit_of_medicine, found.get().getUnit_of_medicine());
        assertEquals("eat 2 times per day", found.get().getHow());
        assertEquals("dont", found.get().getSideEffect());
        assertEquals(20, found.get().getPrice());
        assertEquals(pharmacist, found.get().getPharmacist());
    }


        // How กรณีที่ 2 น้อยกว่า 3 ตัว
        @Test
        void B5903832_testHowSizelessthan2() {
            Drug drug = new Drug();
        try {
            drug.setDrugname("k");
        } catch (ConstraintViolationException e) {
    
            Set<ConstraintViolation<Drug>> result = validator.validate(drug);
    
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Drug> message = result.iterator().next();
            assertEquals("size must be between 3 and 50", message.getMessage());
            assertEquals("How", message.getPropertyPath().toString());
        }
        }
    
        // How กรณีที่ 3 มากกว่า 30 ตัว
        @Test
        void B5903832_testHowSizeMorethan100() {
            Drug drug = new Drug();
        try {
            drug.setDrugname("aaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaO");
        } catch (ConstraintViolationException e) {
    
            Set<ConstraintViolation<Drug>> result = validator.validate(drug);
    
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Drug> message = result.iterator().next();
            assertEquals("size must be between 2 and 100", message.getMessage());
            assertEquals("How", message.getPropertyPath().toString());
            }
        }
    
        // How กรณีที่ 4 ชื่อต้องไม่ว่างเปล่า
        @Test
        void B5903832_testHowMustNotBeNull() {
            Drug drug = new Drug();
        try {
            drug.setDrugname(null);
        } catch (ConstraintViolationException e) {
    
            Set<ConstraintViolation<Drug>> result = validator.validate(drug);
    
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Drug> message = result.iterator().next();
            assertEquals("How must not be null", message.getMessage());
            assertEquals("How", message.getPropertyPath().toString());
            }
        }


        // SideEffect กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5903832_testSideEffectwithCorrect() {
        Drug drug = new Drug();
        Unit_of_medicine unit_of_medicine = unit_of_medicineRepository.findById(2);
        DrugCategory category = drugcategoryRepository.findById(2);
        Pharmacist pharmacist = pharmacistRepository.findById(1);

        drug.setDrugname("paracetamol");
        drug.setCategory(category);
        drug.setUnit_of_medicine(unit_of_medicine);
        drug.setHow("eat 2 times per day");
        drug.setSideEffect("dont");
        drug.setPrice(20);  
        drug.setPharmacist(pharmacist); 

        drug = drugRepository.saveAndFlush(drug);
 
        Optional<Drug> found = drugRepository.findById(drug.getID());
        assertEquals("paracetamol", found.get().getDrugname());
        assertEquals(category, found.get().getCategory());
        assertEquals(unit_of_medicine, found.get().getUnit_of_medicine());
        assertEquals("eat 2 times per day", found.get().getHow());
        assertEquals("dont", found.get().getSideEffect());
        assertEquals(20, found.get().getPrice());
        assertEquals(pharmacist, found.get().getPharmacist());
    }


        // SideEffect กรณีที่ 2 น้อยกว่า 3 ตัว
        @Test
        void B5903832_testSideEffectSizelessthan2() {
            Drug drug = new Drug();
        try {
            drug.setDrugname("k");
        } catch (ConstraintViolationException e) {
    
            Set<ConstraintViolation<Drug>> result = validator.validate(drug);
    
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Drug> message = result.iterator().next();
            assertEquals("size must be between 3 and 200", message.getMessage());
            assertEquals("How", message.getPropertyPath().toString());
        }
        }
    
        //Side Effect  กรณีที่ 3 มากกว่า 200 ตัว
        @Test
        void B5903832_testSideEffectSizeMorethan200() {
            Drug drug = new Drug();
        try {
            drug.setSideEffect("aaaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaOaaaaaaaaaO");
        } catch (ConstraintViolationException e) {
    
            Set<ConstraintViolation<Drug>> result = validator.validate(drug);
    
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Drug> message = result.iterator().next();
            assertEquals("size must be between 2 and 200", message.getMessage());
            assertEquals("How", message.getPropertyPath().toString());
            }
        }
    
        // SeideEffect กรณีที่ 4 ชื่อต้องไม่ว่างเปล่า
        @Test
        void B5903832_testSideEffectMustNotBeNull() {
            Drug drug = new Drug();
        try {
            drug.setSideEffect(null);
        } catch (ConstraintViolationException e) {
    
            Set<ConstraintViolation<Drug>> result = validator.validate(drug);
    
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Drug> message = result.iterator().next();
            assertEquals("SideEffect must not be null", message.getMessage());
            assertEquals("SideEffect", message.getPropertyPath().toString());
            }
        }


        // Price กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5903832_testPriceWithCorrect() {
        Drug drug = new Drug();
        Unit_of_medicine unit_of_medicine = unit_of_medicineRepository.findById(2);
        DrugCategory category = drugcategoryRepository.findById(2);
        Pharmacist pharmacist = pharmacistRepository.findById(1);

        drug.setDrugname("paracetamol");
        drug.setCategory(category);
        drug.setUnit_of_medicine(unit_of_medicine);
        drug.setHow("eat 2 times per day");
        drug.setSideEffect("dont");
        drug.setPrice(20);  
        drug.setPharmacist(pharmacist); 

        drug = drugRepository.saveAndFlush(drug);
 
        Optional<Drug> found = drugRepository.findById(drug.getID());
        assertEquals("paracetamol", found.get().getDrugname());
        assertEquals(category, found.get().getCategory());
        assertEquals(unit_of_medicine, found.get().getUnit_of_medicine());
        assertEquals("eat 2 times per day", found.get().getHow());
        assertEquals("dont", found.get().getSideEffect());
        assertEquals(20, found.get().getPrice());
        assertEquals(pharmacist, found.get().getPharmacist());
    }

    // Price กรณีที่ 2 ใส่ค่า Null
    @Test
    void B5903832_testPriceNotNull() { // ใส่ข้อมูลปกติ
        Drug drug = new Drug();
        try {
            drug.setPrice(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Drug>> result = validator.validate(drug);

            assertEquals(1, result.size());

            ConstraintViolation<Drug> message = result.iterator().next();
            assertEquals("must not be null", message.getMessage());
            assertEquals("drugPrice", message.getPropertyPath().toString());
        }
    }
    
    // Price กรณีที่ 3 ใส่ค่า มากกว่า 0
    @Test
    void B5903832_testPriceMoreThanZero() { // ใส่ข้อมูลปกติ
        Drug drug = new Drug();

        try {
            drug.setPrice(55);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Drug>> result = validator.validate(drug);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Drug> message = result.iterator().next();
            assertEquals("must be less than or equal to 999999", message.getMessage());
            assertEquals("drugPrice", message.getPropertyPath().toString());
        }
    }
    
    // Price กรณี 3 ใส่ค่า 0
    @Test
    void B5903832_testPriceEqualZero() { // ใส่ข้อมูลปกติ
        Drug drug = new Drug();

        try {
            drug.setPrice(0);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Drug>> result = validator.validate(drug);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Drug> message = result.iterator().next();
            assertEquals("must be equal Zero", message.getMessage());
            assertEquals("drugPrice", message.getPropertyPath().toString());
        }
    }


    @Test
    void B5903832_testCategoryNotNull() { // ใส่ข้อมูลปกติ
        Drug drug = new Drug();
        try {
            drug.setCategory(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Drug>> result = validator.validate(drug);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Drug> message = result.iterator().next();
            assertEquals("must be not null", message.getMessage());
            assertEquals("Category", message.getPropertyPath().toString());
        }
    }

    @Test
    void B5903832_testUnit_of_medicineNotNull() { // ใส่ข้อมูลปกติ
        Drug drug = new Drug();
        try {
            drug.setUnit_of_medicine(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Drug>> result = validator.validate(drug);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Drug> message = result.iterator().next();
            assertEquals("must be not null", message.getMessage());
            assertEquals("Category", message.getPropertyPath().toString());
        }
    }

    @Test
    void B5903832_testPharmacistNotNull() { // ใส่ข้อมูลปกติ
        Drug drug = new Drug();
        try {
            drug.setPharmacist(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Drug>> result = validator.validate(drug);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Drug> message = result.iterator().next();
            assertEquals("must be not null", message.getMessage());
            assertEquals("Pharmacist", message.getPropertyPath().toString());
        }
    }




    }


