package com.cpe.backend.controller;

import com.cpe.backend.Entity.DrugDispenseChannel;
import com.cpe.backend.repository.DrugDispenseChannelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DrugDispenseChannelController {

    @Autowired
    private final DrugDispenseChannelRepository drugDispenseChannelRepository;

    public DrugDispenseChannelController(DrugDispenseChannelRepository drugDispenseChannelRepository) {
        this.drugDispenseChannelRepository = drugDispenseChannelRepository;
    }

    @GetMapping("/drugdispensechannel")
    public Collection<DrugDispenseChannel> drugDispenseChannels() {
        return drugDispenseChannelRepository.findAll().stream().collect(Collectors.toList());
    }

}