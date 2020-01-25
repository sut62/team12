package com.cpe.backend;


import com.cpe.backend.Entity.Pharmacist;
import com.cpe.backend.Entity.TitlePharmacist;
import com.cpe.backend.Entity.Province;
import com.cpe.backend.Entity.Sex;
import com.cpe.backend.repository.PharmacistRepository;
import com.cpe.backend.repository.TitlePharmacistRepository;
import com.cpe.backend.repository.ProvinceRepository;
import com.cpe.backend.repository.SexRepository;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ch.qos.logback.core.util.DatePatternToRegexUtil;

import javax.validation.ConstraintViolation;
import javax.validation.UnexpectedTypeException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import java.sql.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PharmacistTest {

    private Validator validator;

    @Autowired
    private  PharmacistRepository pharmacistRepository;
    @Autowired
    private  TitlePharmacistRepository titlepharmacistRepository;
    @Autowired
    private SexRepository sexRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
       factory.getValidator();
    }

     // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    
     @Test
    void B5903917_testID_CaredPharmacistOK() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(19);
    java.sql.Date birthday = new java.sql.Date(1997-10-11);

    Pharmacist pharm = new Pharmacist();
    try { pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("ขขขขขข");
    pharm.setBirthday(birthday);
    pharm.setAge(33);  
    pharm.setAddress("Address"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("Hello@java.com"); 
    } catch (UnexpectedTypeException e){
     pharm = pharmacistRepository.saveAndFlush(pharm);
        Optional<Pharmacist> check = pharmacistRepository.findById(pharm.getId());
        assertEquals("1234567890123", check.get().getId_card());
        assertEquals("ขขขขขข", check.get().getName());
        assertEquals(33, check.get().getAge());
        assertEquals(birthday, check.get().getBirthday());
        assertEquals("17037b@ffsf", check.get().getMail());
        assertEquals("Address", check.get().getAddress());
        assertEquals("0812345678", check.get().getPhone());
        assertEquals(sex, check.get().getSex());
        assertEquals(titlepharmacist, check.get().getTitlePharmacist());
        assertEquals(province, check.get().getProvince());
    }

}
 
@Test
    void B5903917_testID_CARDwithSizelessthan13() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try { pharm.setId_card("123456789012");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("ขขขขขข");
    pharm.setBirthday(birthday);
    pharm.setAge(33);  
    pharm.setAddress("Address"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("17037b@ffsf");  
    } catch (ConstraintViolationException e){
        pharm = pharmacistRepository.saveAndFlush(pharm);
        Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());
       // error message ตรงชนิด และถูก field
       ConstraintViolation<Pharmacist> message = result.iterator().next();
       assertEquals("must match \"\\d{13}\"", message.getMessage());
       assertEquals("Id_card", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testID_CARDwithSizeMorethan13() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try { pharm.setId_card("12345678901234");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("ขขขขขข");
    pharm.setBirthday(birthday);
    pharm.setAge(33);  
    pharm.setAddress("Address"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("17037b@ffsf");  
    } catch (ConstraintViolationException e){

     pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("must match \"\\d{13}\"", message.getMessage());
        assertEquals("Id_card", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testID_CARDwithNotNull() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try {try{ pharm.setId_card(null);
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("ขขขขขข");
    pharm.setBirthday(birthday);
    pharm.setAge(33);  
    pharm.setAddress("Address"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("17037b@ffsf");  
    } catch (NullPointerException e){
    }}catch (UnexpectedTypeException e){
         // NullPointerException + UnexpectedTypeException 
         pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("Must not be null", message.getMessage());
        assertEquals("Id_card", message.getPropertyPath().toString());
}
    }


@Test
    void B5903917_testNamewithNotNull() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try {try{ pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName(null);
    pharm.setBirthday(birthday);
    pharm.setAge(33);  
    pharm.setAddress("Address"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("17037b@ffsf");  
    } catch (NullPointerException e){
    }}catch (UnexpectedTypeException e){
     // NullPointerException + UnexpectedTypeException 
        pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("Must not be null", message.getMessage());
        assertEquals("Name", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testNameLessThan2() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try { pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("k");
    pharm.setBirthday(birthday);
    pharm.setAge(33);  
    pharm.setAddress("Address"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("17037b@ffsf");  
    } catch (ConstraintViolationException e){
     
        pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("size must be between 2 and 50", message.getMessage());
        assertEquals("Name", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testNameMoreThan50() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try { pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("สุดสวยแสนดีสุดสวยแสนดีสุดสวยแสนดีสุดสวยแสนดีสุดสวยแสนดีมาก");
    pharm.setBirthday(birthday);
    pharm.setAge(33);  
    pharm.setAddress("Address"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("17037b@ffsf");  
    } catch (ConstraintViolationException e){
     
        pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("size must be between 2 and 50", message.getMessage());
        assertEquals("Name", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testAgeLessThan1() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try { pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("k");
    pharm.setBirthday(birthday);
    pharm.setAge(0);  
    pharm.setAddress("Address"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("17037b@ffsf");  
    } catch (ConstraintViolationException e){
     
        pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("must be greater than or equal to 1", message.getMessage());
        assertEquals("Age", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testAgeMoreThan125() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try { pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("k");
    pharm.setBirthday(birthday);
    pharm.setAge(126);  
    pharm.setAddress("Address"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("17037b@ffsf");  
    } catch (ConstraintViolationException e){
     
        pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("must be less than or equal to 125", message.getMessage());
        assertEquals("Age", message.getPropertyPath().toString());
    }
}

@Test
    void B5903917_testAgewithNotNull() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try {try{ pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("สายรุ้ง");
    pharm.setBirthday(birthday);
    pharm.setAge(null);  
    pharm.setAddress("Address"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("17037b@ffsf");  
    } catch (NullPointerException e){
    }}catch (UnexpectedTypeException e){
     // NullPointerException + UnexpectedTypeException 
        pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("Must not be null", message.getMessage());
        assertEquals("Age", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testAddresswithNotNull() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try {try{ pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("สายรุ้ง");
    pharm.setBirthday(birthday);
    pharm.setAge(20);  
    pharm.setAddress(null); 
    pharm.setPhone("0812345678");  
    pharm.setMail("17037b@ffsf");  
    } catch (NullPointerException e){
    }}catch (UnexpectedTypeException e){
     // NullPointerException + UnexpectedTypeException 
        pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("Must not be null", message.getMessage());
        assertEquals("Address", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testAddressMoreThan100() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try { pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("k");
    pharm.setBirthday(birthday);
    pharm.setAge(126);  
    pharm.setAddress("Addresssed1Addresssed2Addresssed3Addresssed4"
    +"Addresssed5Addresssed6Addresssed7Addresssed8Addresssed9Addresssed10"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("17037b@ffsf");  
    } catch (ConstraintViolationException e){
     
        pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("size must be between 5 and 100", message.getMessage());
        assertEquals("Address", message.getPropertyPath().toString());
    }
}

@Test
    void B5903917_testAddressLessThan5() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try { pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("k");
    pharm.setBirthday(birthday);
    pharm.setAge(0);  
    pharm.setAddress("Address"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("17037b@ffsf");  
    } catch (ConstraintViolationException e){
     
        pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("size must be between 5 and 100", message.getMessage());
        assertEquals("Address", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testPhonewithNotNull() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try {try{ pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("สายรุ้ง");
    pharm.setBirthday(birthday);
    pharm.setAge(20);  
    pharm.setAddress("libralya"); 
    pharm.setPhone(null);  
    pharm.setMail("17037b@ffsf");  
    } catch (NullPointerException e){
    }}catch (UnexpectedTypeException e){
     // NullPointerException + UnexpectedTypeException 
        pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("Must not be null", message.getMessage());
        assertEquals("Phone", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testPhonewithSizeMorethan10() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try { pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("ขขขขขข");
    pharm.setBirthday(birthday);
    pharm.setAge(33);  
    pharm.setAddress("Address"); 
    pharm.setPhone("081234567890");  
    pharm.setMail("17037b@ffsf");  
    } catch (ConstraintViolationException e){

     pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("size must be only 10", message.getMessage());
        assertEquals("Phone", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testPhonewithSizeLessthan10() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try { pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("ขขขขขข");
    pharm.setBirthday(birthday);
    pharm.setAge(33);  
    pharm.setAddress("Address"); 
    pharm.setPhone("081234");  
    pharm.setMail("17037b@ffsf");  
    } catch (ConstraintViolationException e){

     pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("size must be only 10", message.getMessage());
        assertEquals("Phone", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testMailwithSizeMorethan50() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try { pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("ขขขขขข");
    pharm.setBirthday(birthday);
    pharm.setAge(33);  
    pharm.setAddress("Address"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("qqqqqqqqqqrrrrrrrrrr17037bffsf@ttttttttttuuuuuuuuuuaaaaaa");  
    } catch (ConstraintViolationException e){

     pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("size must be between 5 and 50", message.getMessage());
        assertEquals("Mail", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testMailwithNotNull() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try {try{ pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("สายรุ้ง");
    pharm.setBirthday(birthday);
    pharm.setAge(20);  
    pharm.setAddress("libralya"); 
    pharm.setPhone("0812345698");  
    pharm.setMail(null);  
    } catch (NullPointerException e){
    }}catch (UnexpectedTypeException e){
     // NullPointerException + UnexpectedTypeException 
        pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("Must not be null", message.getMessage());
        assertEquals("Mail", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testMailwithSizeLessthan5() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try { pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("ขขขขขข");
    pharm.setBirthday(birthday);
    pharm.setAge(33);  
    pharm.setAddress("Address"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("17@1");  
    } catch (ConstraintViolationException e){

     pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("size must be between 5 and 50", message.getMessage());
        assertEquals("Mail", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testBirthdaywithNotNull() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-11);

    Pharmacist pharm = new Pharmacist();
    try {try{ pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("สายรุ้ง");
    pharm.setBirthday(null);
    pharm.setAge(20);  
    pharm.setAddress("libralya"); 
    pharm.setPhone("0812345698");  
    pharm.setMail("rrygttg@gfgdsg.bbf");  
    } catch (NullPointerException e){
    }}catch (UnexpectedTypeException e){
     // NullPointerException + UnexpectedTypeException 
        pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("Must not be null", message.getMessage());
        assertEquals("Birthday", message.getPropertyPath().toString());
    }

}

 @Test
    void B5903917_testBirthdayMustNotBe11Digits() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(1111-11-111);

    Pharmacist pharm = new Pharmacist();
    try { pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("ขขขขขข");
    pharm.setBirthday(birthday);
    pharm.setAge(33);  
    pharm.setAddress("Addrefss"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("ffr2237@fg.661");  
    } catch (ConstraintViolationException e){

     pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", message.getMessage());
        assertEquals("Birthday", message.getPropertyPath().toString());
    }

}

@Test
    void B5903917_testBirthdayMustNotBe9Digits() {

    Pharmacist pharmacist = new Pharmacist();
    TitlePharmacist titlepharmacist = titlepharmacistRepository.findById(1);
    Sex sex = sexRepository.findById(1);
    Province province =  provinceRepository.findById(53);
    java.sql.Date birthday = new java.sql.Date(111-11-11);

    Pharmacist pharm = new Pharmacist();
    try { pharm.setId_card("1234567890123");
    pharm.setProvince(province);
    pharm.setSex(sex);
    pharm.setTitlePharmacist(titlepharmacist);
    pharm.setName("ขขขขขข");
    pharm.setBirthday(birthday);
    pharm.setAge(33);  
    pharm.setAddress("Address"); 
    pharm.setPhone("0812345678");  
    pharm.setMail("1bfdb7@.gdgd1");  
    } catch (ConstraintViolationException e){

     pharm = pharmacistRepository.saveAndFlush(pharm);
         Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharm);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pharmacist> message = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", message.getMessage());
        assertEquals("Birthday", message.getPropertyPath().toString());
    }

}

@Test
    void B5903900_testTitlePharmacistNotNull() { // ใส่ข้อมูลปกติ
        Pharmacist pharmacist = new Pharmacist();
        try {
            pharmacist.setTitlePharmacist(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharmacist);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Pharmacist> message = result.iterator().next();
            assertEquals("must be not null", message.getMessage());
            assertEquals("TitlePharmacist", message.getPropertyPath().toString());
        }
    }


    @Test
    void B5903900_testSexNotNull() { // ใส่ข้อมูลปกติ
        Pharmacist pharmacist = new Pharmacist();
        try {
            pharmacist.setSex(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharmacist);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Pharmacist> message = result.iterator().next();
            assertEquals("must be not null", message.getMessage());
            assertEquals("Sex", message.getPropertyPath().toString());
        }
    }

    @Test
    void B5903900_testProviceNotNull() { // ใส่ข้อมูลปกติ
        Pharmacist pharmacist = new Pharmacist();
        try {
            pharmacist.setProvince(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Pharmacist>> result = validator.validate(pharmacist);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<Pharmacist> message = result.iterator().next();
            assertEquals("must be not null", message.getMessage());
            assertEquals("Province", message.getPropertyPath().toString());
        }
    }
}
