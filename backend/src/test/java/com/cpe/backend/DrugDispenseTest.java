package com.cpe.backend;

import com.cpe.backend.Entity.DrugDispense;
import com.cpe.backend.Entity.DrugDispenseChannel;
import com.cpe.backend.Entity.TitlePharmacist;
import com.cpe.backend.Entity.Pharmacist;

import com.cpe.backend.repository.DrugDispenseRepository;
import com.cpe.backend.repository.DrugDispenseChannelRepository;
import com.cpe.backend.repository.TitlePharmacistRepository;
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
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class DrugDispenseTest {
    private Validator validator;

    @Autowired
    private DrugDispenseRepository drugdispenseRepository;

    @Autowired
    private DrugDispenseChannelRepository drugDispenseChannelRepository;

    @Autowired
    private TitlePharmacistRepository titlepharmacistRepository;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5915477_testDrugDispenseComplete()  {
        DrugDispense drugdispense = new DrugDispense();
        DrugDispenseChannel drugdispensechannel = drugDispenseChannelRepository.findById(3);
        TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(2);
        Pharmacist pharmacist = pharmacistRepository.findById(1);
        
        drugdispense.setDrugdispensechannel(drugdispensechannel);
        drugdispense.setTitlePharmacist(titlepharmacist);
        drugdispense.setPharmacist(pharmacist);
        drugdispense.setReciever_name("ประวีร์ ยุทธวีระวงศ์");

        drugdispense = drugdispenseRepository.saveAndFlush(drugdispense);

        Optional<DrugDispense> found = drugdispenseRepository.findById(drugdispense.getId());

        assertEquals(drugdispensechannel, found.get().getDrugdispensechannel());
        assertEquals(titlepharmacist, found.get().getTitlePharmacist());
        assertEquals(pharmacist, found.get().getPharmacist());
        assertEquals("ประวีร์ ยุทธวีระวงศ์", found.get().getReciever_name());
    }

    @Test
    void B5915477_testRecieve_nameMustNotBeNull(){
        DrugDispense drugdispense = new DrugDispense();
        DrugDispenseChannel drugdispensechannel = drugDispenseChannelRepository.findById(3);
        TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(2);
        Pharmacist pharmacist = pharmacistRepository.findById(1);
        
        try{
            drugdispense.setDrugdispensechannel(drugdispensechannel);
            drugdispense.setTitlePharmacist(titlepharmacist);
            drugdispense.setPharmacist(pharmacist);
            drugdispense.setReciever_name(null);
        }
        catch(ConstraintViolationException e){
            Set<ConstraintViolation<DrugDispense>> result = validator.validate(drugdispense);

            assertEquals(1, result.size());

            ConstraintViolation<DrugDispense> message = result.iterator().next();
            assertEquals("must not be null", message.getMessage());
            assertEquals("reciever_name",message.getPropertyPath().toString());
        }
               
    }

    @Test
    void B5915477_testRecieverNameMustBeUnique(){
        DrugDispense d1 = new DrugDispense();
        DrugDispenseChannel drugdispensechannel = drugDispenseChannelRepository.findById(3);
        TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(2);
        Pharmacist pharmacist = pharmacistRepository.findById(1);
        
        try{
            d1.setDrugdispensechannel(drugdispensechannel);
            d1.setTitlePharmacist(titlepharmacist);
            d1.setPharmacist(pharmacist);
            d1.setReciever_name("ประวีร์ ยุทธวีระวงศ์");
            drugdispenseRepository.saveAndFlush(d1);            
        }
        catch(DataIntegrityViolationException e){
            // คาดหวังว่า DataIntegrityViolationException จะถูก throw
            assertThrows(DataIntegrityViolationException.class, () -> {
                DrugDispense d2 = new DrugDispense();
                
                d2.setDrugdispensechannel(drugdispensechannel);
                d2.setTitlePharmacist(titlepharmacist);
                d2.setPharmacist(pharmacist);
                d2.setReciever_name("ประวีร์ ยุทธวีระวงศ์");
                drugdispenseRepository.saveAndFlush(d2);               
            });
        }        
        
    }

    @Test
    void B5915477_testRecieverNameNotLessThan2(){
        DrugDispense drugdispense = new DrugDispense();
        DrugDispenseChannel drugdispensechannel = drugDispenseChannelRepository.findById(3);
        TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(2);
        Pharmacist pharmacist = pharmacistRepository.findById(1);
        
        try{
            drugdispense.setDrugdispensechannel(drugdispensechannel);
            drugdispense.setTitlePharmacist(titlepharmacist);
            drugdispense.setPharmacist(pharmacist);
            drugdispense.setReciever_name("ก");
        }
        catch(ConstraintViolationException e){
            Set<ConstraintViolation<DrugDispense>> result = validator.validate(drugdispense);
		
            assertEquals(1, result.size());
            
            ConstraintViolation<DrugDispense> v = result.iterator().next();
            assertEquals("size must be between 2 and 30", v.getMessage());
            assertEquals("reciever_name", v.getPropertyPath().toString());
        }        
    }

    @Test
    void B5915477_testRecieverNameNotMoreThan30(){
        DrugDispense drugdispense = new DrugDispense();
        DrugDispenseChannel drugdispensechannel = drugDispenseChannelRepository.findById(3);
        TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(2);
        Pharmacist pharmacist = pharmacistRepository.findById(1);
        
        try{
            drugdispense.setDrugdispensechannel(drugdispensechannel);
            drugdispense.setTitlePharmacist(titlepharmacist);
            drugdispense.setPharmacist(pharmacist);
            drugdispense.setReciever_name("กกกกกกกกกกกกกกกกกกกกกกกกกกกกกกก");
        }
        catch(ConstraintViolationException e){
             Set<ConstraintViolation<DrugDispense>> result = validator.validate(drugdispense);
            
            assertEquals(1, result.size());
            
            ConstraintViolation<DrugDispense> v = result.iterator().next();
            assertEquals("size must be between 2 and 30", v.getMessage());
            assertEquals("reciever_name", v.getPropertyPath().toString());
        }
    }
    
    @Test
    void B5915477_testDrugDispenseChannelMustNotBeNull() { // ใส่ข้อมูลปกติ
        DrugDispense drugdispense = new DrugDispense();
        try {
            drugdispense.setDrugdispensechannel(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<DrugDispense>> result = validator.validate(drugdispense);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<DrugDispense> v = result.iterator().next();
            assertEquals("must be not null", v.getMessage());
            assertEquals("drugdispenseChannel", v.getPropertyPath().toString());
        }
    }

    @Test
    void B5915477_testTitlePharmacistMustNotBeNull() { // ใส่ข้อมูลปกติ
        DrugDispense drugdispense = new DrugDispense();
        try {
            drugdispense.setTitlePharmacist(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<DrugDispense>> result = validator.validate(drugdispense);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<DrugDispense> v = result.iterator().next();
            assertEquals("must be not null", v.getMessage());
            assertEquals("titlepharmacist", v.getPropertyPath().toString());
        }
    }

    @Test
    void B5915477_testPharmacistMustNotBeNull() { // ใส่ข้อมูลปกติ
        DrugDispense drugdispense = new DrugDispense();
        try {
            drugdispense.setPharmacist(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<DrugDispense>> result = validator.validate(drugdispense);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<DrugDispense> v = result.iterator().next();
            assertEquals("must be not null", v.getMessage());
            assertEquals("pharmacist", v.getPropertyPath().toString());
        }
    }

}
