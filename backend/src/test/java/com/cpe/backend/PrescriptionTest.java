package com.cpe.backend;

import com.cpe.backend.Entity.Doctor;
import com.cpe.backend.Entity.Prescription;
import com.cpe.backend.Entity.TitleName;
import com.cpe.backend.Entity.Unit_of_medicine;
import com.cpe.backend.Entity.Drug;
import com.cpe.backend.repository.DrugRepository;
import com.cpe.backend.repository.DoctorRepository;
import com.cpe.backend.repository.PrescriptionRepository;
import com.cpe.backend.repository.TitleNameRepository;
import com.cpe.backend.repository.Unit_of_medicineRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PrescriptionTest {

    private Validator validator;
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private TitleNameRepository titleNameRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private Unit_of_medicineRepository unit_of_medicineRepository;
    @Autowired
    private DrugRepository drugRepository;
    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        factory.getValidator();
    }

    //ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5905713_testPrescriptionComplete() {
        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);

        prescription.setPatient_ID("HN12345678");
        prescription.setTitleName(titleName);
        prescription.setName("สโรชา สุขสบาย");
        prescription.setAge(22);
        prescription.setDrugallergy("not");
        prescription.setDoctor_name(doctor_name);
        prescription.setDrug(drug);
        prescription.setAmount(10);
        prescription.setUnit_of_medicine(unit);

        prescription = prescriptionRepository.saveAndFlush(prescription);

        Optional<Prescription> found = prescriptionRepository.findById(prescription.getId());
        assertEquals("HN12345678", found.get().getPatient_ID());
        assertEquals(titleName, found.get().getTitleName());
        assertEquals("สโรชา สุขสบาย", found.get().getName());
        assertEquals(22, found.get().getAge());
        assertEquals("not", found.get().getDrugallergy());
        assertEquals(doctor_name, found.get().getDoctor_name());
        assertEquals(drug, found.get().getDrug());
        assertEquals(10, found.get().getAmount());
        assertEquals(unit, found.get().getUnit_of_medicine());
    }
    //กรณี Patient_ID ต้องไม่เป็นค่าว่าง
    @Test
    void B5905713_testPatient_IDMustNotBeNull() {

        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);
        
        try {
            prescription.setPatient_ID(null);
            prescription.setTitleName(titleName);
            prescription.setName("สโรชา สุขสบาย");
            prescription.setAge(22);
            prescription.setDrugallergy("not");
            prescription.setDoctor_name(doctor_name);
            prescription.setDrug(drug);
            prescription.setAmount(10);
            prescription.setUnit_of_medicine(unit);

        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("Patient_ID", v.getPropertyPath().toString());

        }
    }
    //กรณี Patient_ID ตัวแรกต้องไม่เป็น A
    @Test
    void B5905713_testPatient_IDFirstCharacterA(){
        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);
        try {
            prescription.setPatient_ID("AN12345678");
            prescription.setTitleName(titleName);
            prescription.setName("สโรชา สุขสบาย");
            prescription.setAge(22);
            prescription.setDrugallergy("not");
            prescription.setDoctor_name(doctor_name);
            prescription.setDrug(drug);
            prescription.setAmount(10);
            prescription.setUnit_of_medicine(unit);

        }catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> message = result.iterator().next();
            assertEquals("The first must be \"HN\\d{8}\"",message.getMessage());
            assertEquals("Patient_ID",message.getPropertyPath().toString());
        }

    }
    //กรณี Patient_ID ตัวที่สองต้องไม่เป็น B
    @Test
    void B5905713_testPatient_IDSecondCharacterB(){
        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);
        try {
            prescription.setPatient_ID("HB12345678");
            prescription.setTitleName(titleName);
            prescription.setName("สโรชา สุขสบาย");
            prescription.setAge(22);
            prescription.setDrugallergy("not");
            prescription.setDoctor_name(doctor_name);
            prescription.setDrug(drug);
            prescription.setAmount(10);
            prescription.setUnit_of_medicine(unit);

        }catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> message = result.iterator().next();
            assertEquals("The first must be \"HN\\d{8}\"",message.getMessage());
            assertEquals("Patient_ID",message.getPropertyPath().toString());
        }

    }
    //กรณ๊ Name ต้องไม่เป็นค่าว่าง
    @Test
    void B5905713_testNameMustNotBeNull(){
        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);
        
        try {
            prescription.setPatient_ID("HN12345678");
            prescription.setTitleName(titleName);
            prescription.setName(null);
            prescription.setAge(22);
            prescription.setDrugallergy("not");            
            prescription.setDoctor_name(doctor_name);
            prescription.setDrug(drug);
            prescription.setAmount(10);
            prescription.setUnit_of_medicine(unit);

        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("Name", v.getPropertyPath().toString());

        }
    }
    //กรณี Name ต้องไม่น้อยกว่า 5 ตัวอักษร
    @Test
    void B5905713_testNameSizeLassthan5(){
        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);
        try {
            prescription.setPatient_ID("HB12345678");
            prescription.setTitleName(titleName);
            prescription.setName("มี D");
            prescription.setAge(22);
            prescription.setDrugallergy("not");
            prescription.setDoctor_name(doctor_name);
            prescription.setDrug(drug);
            prescription.setAmount(10);
            prescription.setUnit_of_medicine(unit);

        }catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> message = result.iterator().next();
            assertEquals("size must be between 5 and 50",message.getMessage());
            assertEquals("Name",message.getPropertyPath().toString());
        }
    }
    //กรณี Name ต้องไม่มากกว่า 50 ตัวอักษร
    @Test
    void B5905713_testNameSizeMorethan50(){
        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);
        try {
            prescription.setPatient_ID("HB12345678");
            prescription.setTitleName(titleName);
            prescription.setName("โรงพยาบาลมหาวิทยาลัยเทคโนโลยีสุรนารี นครราชสีมาาาาา");
            prescription.setAge(22);
            prescription.setDrugallergy("not");
            prescription.setDoctor_name(doctor_name);
            prescription.setDrug(drug);
            prescription.setAmount(10);
            prescription.setUnit_of_medicine(unit);

        }catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> message = result.iterator().next();
            assertEquals("size must be between 5 and 50",message.getMessage());
            assertEquals("Name",message.getPropertyPath().toString());
        }
    }
    //กรณี Age ต้องไม่เป็นค่าว่าง
    @Test
    void B5905713_testAgeMustNotBeNull(){
        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);
        
        try {
            prescription.setPatient_ID("HN12345678");
            prescription.setTitleName(titleName);
            prescription.setName("สโรชา สุขสบาย");
            prescription.setAge(null);
            prescription.setDrugallergy("not");
            prescription.setDoctor_name(doctor_name);
            prescription.setDrug(drug);
            prescription.setAmount(10);
            prescription.setUnit_of_medicine(unit);

        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("Age", v.getPropertyPath().toString());

        }
    }
    //กรณี Age ต้องไม่น้อยกว่า 1 ปี
    @Test
    void B5905713_testAgeMin1(){
        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);
        
        try {
            prescription.setPatient_ID("HN12345678");
            prescription.setTitleName(titleName);
            prescription.setName("สโรชา สุขสบาย");
            prescription.setAge(0);
            prescription.setDrugallergy("not");
            prescription.setDoctor_name(doctor_name);
            prescription.setDrug(drug);
            prescription.setAmount(10);
            prescription.setUnit_of_medicine(unit);

        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> v = result.iterator().next();
            assertEquals("must be greater than or equal to 1", v.getMessage());
            assertEquals("Age", v.getPropertyPath().toString());

        }
    }
    //กรณี Age ต้องไม่มากกว่า 122 ปี
    @Test
    void B5905713_testAgeMax122(){
        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);
        
        try {
            prescription.setPatient_ID("HN12345678");
            prescription.setTitleName(titleName);
            prescription.setName("สโรชา สุขสบาย");
            prescription.setAge(123);
            prescription.setDrugallergy("not");
            prescription.setDoctor_name(doctor_name);
            prescription.setDrug(drug);
            prescription.setAmount(10);
            prescription.setUnit_of_medicine(unit);

        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> v = result.iterator().next();
            assertEquals("must be less than or equal to 122", v.getMessage());
            assertEquals("Age", v.getPropertyPath().toString());

        }
    }
     //กรณี Drugallergy ต้องไม่เป็นค่าว่าง
    @Test
    void B5905713_testDrugallergyMustNotBeNull(){
        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);
        
        try {
            prescription.setPatient_ID("HN12345678");
            prescription.setTitleName(titleName);
            prescription.setName("สโรชา สุขสบาย");
            prescription.setAge(22);
            prescription.setDrugallergy(null);
            prescription.setDoctor_name(doctor_name);
            prescription.setDrug(drug);
            prescription.setAmount(10);
            prescription.setUnit_of_medicine(unit);

        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("Drugallergy", v.getPropertyPath().toString());

        }
    }
    //กรณี Drugallergy ตัวแรกต้องไม่เป็นตัวเลข
    @Test
    void B5905713_testDrugallergyMutNotBeNumber(){
        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);
        
        try {
            prescription.setPatient_ID("HN12345678");
            prescription.setTitleName(titleName);
            prescription.setName("สโรชา สุขสบาย");
            prescription.setAge(22);
            prescription.setDrugallergy("paracetamol56");
            prescription.setDoctor_name(doctor_name);
            prescription.setDrug(drug);
            prescription.setAmount(10);
            prescription.setUnit_of_medicine(unit);

        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> v = result.iterator().next();
            assertEquals("must match \"[a-z]*\"", v.getMessage());
            assertEquals("Drugallergy", v.getPropertyPath().toString());
        }
    }
    //กรณี Drugallergy ต้องมีลักษณะตาม Pattern
    @Test
    void B5905713_testDrugallergyMustNotBePattern(){
        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);
        
        try {
            prescription.setPatient_ID("HN12345678");
            prescription.setTitleName(titleName);
            prescription.setName("สโรชา สุขสบาย");
            prescription.setAge(22);
            prescription.setDrugallergy("!@#$%^&*");
            prescription.setDoctor_name(doctor_name);
            prescription.setDrug(drug);
            prescription.setAmount(10);
            prescription.setUnit_of_medicine(unit);

        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> v = result.iterator().next();
            assertEquals("must match \"[a-z]*\"", v.getMessage());
            assertEquals("Drugallergy", v.getPropertyPath().toString());
        }
    }
    //กรณี Amount ต้องไม่เป็นค่าว่าง
    @Test
    void B5905713_testAmountMustNotBeNull(){
        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);
        
        try {
            prescription.setPatient_ID("HN12345678");
            prescription.setTitleName(titleName);
            prescription.setName("สโรชา สุขสบาย");
            prescription.setAge(22);
            prescription.setDrugallergy("not");
            prescription.setDoctor_name(doctor_name);
            prescription.setDrug(drug);
            prescription.setAmount(null);
            prescription.setUnit_of_medicine(unit);

        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("Amount", v.getPropertyPath().toString());
        }
    }
    //กรณี Amount ต้องไม่น้อยกว่า 1
    @Test
    void B5905713_testAmountMin1(){
        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);
        
        try {
            prescription.setPatient_ID("HN12345678");
            prescription.setTitleName(titleName);
            prescription.setName("สโรชา สุขสบาย");
            prescription.setAge(22);
            prescription.setDrugallergy("not");
            prescription.setDoctor_name(doctor_name);
            prescription.setDrug(drug);
            prescription.setAmount(0);
            prescription.setUnit_of_medicine(unit);

        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> v = result.iterator().next();
            assertEquals("must be greater than or equal to 1", v.getMessage());
            assertEquals("Amount", v.getPropertyPath().toString());

        }
    }
    //กรณี Amount ต้องไม่น้อยกว่า 1
    @Test
    void B5905713_testAmountMax100(){
        Prescription prescription = new Prescription();
        TitleName titleName = titleNameRepository.findById(5);
        Doctor doctor_name = doctorRepository.findById(1);
        Drug drug = drugRepository.findById(1);
        Unit_of_medicine unit = unit_of_medicineRepository.findById(2);
        
        try {
            prescription.setPatient_ID("HN12345678");
            prescription.setTitleName(titleName);
            prescription.setName("สโรชา สุขสบาย");
            prescription.setAge(22);
            prescription.setDrugallergy("not");
            prescription.setDoctor_name(doctor_name);
            prescription.setDrug(drug);
            prescription.setAmount(101);
            prescription.setUnit_of_medicine(unit);

        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> v = result.iterator().next();
            assertEquals("must be less than or equal to 100", v.getMessage());
            assertEquals("Amount", v.getPropertyPath().toString());

        }
    }
    //cobobox TitleName ต้องไม่เป็นค่าว่าง
    @Test
    void B5905713_testTitleNameMustNotBeNull(){
        Prescription prescription = new Prescription();
        
        try {
            prescription.setTitleName(null);
        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("TitleName", v.getPropertyPath().toString());
        }
    }
    //cobobox Doctor ต้องไม่เป็นค่าว่าง
    @Test
    void B5905713_testDoctorMustNotBeNull(){
        Prescription prescription = new Prescription();
        
        try {
            prescription.setDoctor_name(null);
        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("Doctor", v.getPropertyPath().toString());
        }
    }
    //cobobox Unit_of_medicine ต้องไม่เป็นค่าว่าง
    @Test
    void B5905713_testUnit_of_medicineMustNotBeNull(){
        Prescription prescription = new Prescription();
        
        try {
            prescription.setUnit_of_medicine(null);
        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Prescription>> result = validator.validate(prescription);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Prescription> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("Unit_of_medicine", v.getPropertyPath().toString());
        }
    }
}