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

import com.cpe.backend.Entity.RecordBill;
import com.cpe.backend.Entity.Cashier;
import com.cpe.backend.Entity.PaymentChannel;
import com.cpe.backend.Entity.MedicalRight;
import com.cpe.backend.repository.RecordBillRepository;
import com.cpe.backend.repository.CashierRepository;
import com.cpe.backend.repository.PaymentChannelRepository;
import com.cpe.backend.repository.MedicalRightRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RecordBillController {
    @Autowired
    private final RecordBillRepository recordBillRepository;
    @Autowired
    private CashierRepository cashierRepository;
    @Autowired
    private PaymentChannelRepository paymentChannelRepository;
    @Autowired
    private MedicalRightRepository medicalRightRepository;

    RecordBillController(RecordBillRepository recordBillRepository) {
        this.recordBillRepository = recordBillRepository;
    }

    @GetMapping("/recordBill")
    public Collection<RecordBill> recordBills() {
        return recordBillRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/recordBill/{MedicalRight_id}/{PaymentChannel_id}/{Cashier_id}/{TotalPrice}")
    public RecordBill newRecordBill(RecordBill newRecordBill, 
            @PathVariable Integer TotalPrice,
            @PathVariable long MedicalRight_id, 
            @PathVariable long PaymentChannel_id,
            @PathVariable long Cashier_id) {

        Cashier cashier = cashierRepository.findById(Cashier_id);
        PaymentChannel paymentChannel = paymentChannelRepository.findById(PaymentChannel_id);
        MedicalRight medicalRight = medicalRightRepository.findById(MedicalRight_id);

        newRecordBill.setCashier(cashier);
        newRecordBill.setPaymentChannel(paymentChannel);
        newRecordBill.setMedicalRight(medicalRight);
        newRecordBill.setTotalPrice(TotalPrice);
        newRecordBill.setCreateDate(new Date());

        return recordBillRepository.save(newRecordBill); // บันทึก Objcet ชื่อ RecordBill

    }
}