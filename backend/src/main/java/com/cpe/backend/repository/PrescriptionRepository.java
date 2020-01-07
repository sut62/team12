package com.cpe.backend.repository;

import com.cpe.backend.Entity.Prescription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Prescription findById(long id);
}