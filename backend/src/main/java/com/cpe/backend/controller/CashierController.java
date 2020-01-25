package com.cpe.backend.controller;

import com.cpe.backend.Entity.Cashier;
import com.cpe.backend.repository.CashierRepository;

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
public class CashierController {

    @Autowired
    private final CashierRepository cashierRepository;

    public CashierController(CashierRepository cashierRepository) {
        this.cashierRepository = cashierRepository;
    }

    @GetMapping("/cashier")
    public Collection<Cashier> cashiers() {
        return cashierRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/cashier/{id}")
    public Optional<Cashier> cashierss(@PathVariable Long id) {
        Optional<Cashier> cashier = cashierRepository.findById(id);
        return cashier;

    }
}