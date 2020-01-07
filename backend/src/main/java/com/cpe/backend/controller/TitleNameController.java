package com.cpe.backend.controller;

import com.cpe.backend.Entity.TitleName;
import com.cpe.backend.repository.TitleNameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TitleNameController {

    @Autowired
    private final TitleNameRepository titleNameRepository;

    public TitleNameController(TitleNameRepository titleNameRepository) {
        this.titleNameRepository = titleNameRepository;
    }

    @GetMapping("/titleName")
    public Collection<TitleName> Severity_Levels() {
        return titleNameRepository.findAll().stream().collect(Collectors.toList());
    }

}