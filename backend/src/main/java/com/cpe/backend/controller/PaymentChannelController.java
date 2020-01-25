package com.cpe.backend.controller;

import com.cpe.backend.Entity.PaymentChannel;
import com.cpe.backend.repository.PaymentChannelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PaymentChannelController {

    @Autowired
    private final PaymentChannelRepository paymentChannelRepository;

    public PaymentChannelController(PaymentChannelRepository paymentChannelRepository) {
        this.paymentChannelRepository = paymentChannelRepository;
    }

    @GetMapping("/paymentChannel")
    public Collection<PaymentChannel> paymentChannels() {
        return paymentChannelRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/paymentChannel/{id}")
    public Optional<PaymentChannel> PaymentChannelss(@PathVariable Long id) {
        Optional<PaymentChannel> paymentchannel = paymentChannelRepository.findById(id);
        return paymentchannel;
    }

}