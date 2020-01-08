package com.cpe.backend.repository;

import com.cpe.backend.Entity.MedicalRight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MedicalRightRepository extends JpaRepository<MedicalRight, Long> {
    MedicalRight findById(long id);
}