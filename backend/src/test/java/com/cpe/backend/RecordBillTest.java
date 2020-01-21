package com.cpe.backend;

import com.cpe.backend.Entity.RecordBill;
import com.cpe.backend.Entity.Cashier;
import com.cpe.backend.Entity.PaymentChannel;
import com.cpe.backend.Entity.MedicalRight;
import com.cpe.backend.Entity.Prescription;
import com.cpe.backend.repository.PrescriptionRepository;
import com.cpe.backend.repository.CashierRepository;
import com.cpe.backend.repository.RecordBillRepository;
import com.cpe.backend.repository.PaymentChannelRepository;
import com.cpe.backend.repository.MedicalRightRepository;

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
public class RecordBillTest{

    private Validator validator;
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private PaymentChannelRepository paymentChannelRepository;
    @Autowired
    private CashierRepository cashierRepository;
    @Autowired
    private MedicalRightRepository medicalRightRepository;
    @Autowired
    private RecordBillRepository recordBillRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        factory.getValidator();
    }
    @Test
    void B5903900_testRecordBillComplete() {
        RecordBill recordBill = new RecordBill();
        PaymentChannel paymentChannel = paymentChannelRepository.findById(3);
        Cashier cashier = cashierRepository.findById(3);
        MedicalRight medicalRight = medicalRightRepository.findById(3);
        Prescription prescription = prescriptionRepository.findById(120001);

        recordBill.setPrescription(prescription);
        recordBill.setCashier(cashier);
        recordBill.setPaymentChannel(paymentChannel);
        recordBill.setMedicalRight(medicalRight);
        recordBill.setDrugPrice(40);

        recordBill = recordBillRepository.saveAndFlush(recordBill);

        Optional<RecordBill> found = recordBillRepository.findById(recordBill.getID());
        assertEquals(cashier, found.get().getCashier());
        assertEquals(40, found.get().getDrugPrice());
        assertEquals(paymentChannel, found.get().getPaymentChannel());
        assertEquals(medicalRight, found.get().getMedicalRight());
        assertEquals(prescription, found.get().getPrescription());
    }

    @Test
    void B5903900_testDrugPriceNotNull() { // ใส่ข้อมูลปกติ
        RecordBill recordBill = new RecordBill();
        try {
            recordBill.setDrugPrice(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<RecordBill>> result = validator.validate(recordBill);

            assertEquals(1, result.size());

            ConstraintViolation<RecordBill> message = result.iterator().next();
            assertEquals("must not be null", message.getMessage());
            assertEquals("drugPrice", message.getPropertyPath().toString());
        }
    }
    
    @Test
    void B5903900_testDrugPriceMoreThanZero() { // ใส่ข้อมูลปกติ
        RecordBill recordBill = new RecordBill();

        try {
            recordBill.setDrugPrice(123);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<RecordBill>> result = validator.validate(recordBill);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<RecordBill> v = result.iterator().next();
            assertEquals("must be less than or equal to 999999", v.getMessage());
            assertEquals("drugPrice", v.getPropertyPath().toString());
        }
    }
    
    @Test
    void B5903900_testDrugPriceEqualZero() { // ใส่ข้อมูลปกติ
        RecordBill recordBill = new RecordBill();

        try {
            recordBill.setDrugPrice(0);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<RecordBill>> result = validator.validate(recordBill);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<RecordBill> v = result.iterator().next();
            assertEquals("must be equal Zero", v.getMessage());
            assertEquals("drugPrice", v.getPropertyPath().toString());
        }
    }

}