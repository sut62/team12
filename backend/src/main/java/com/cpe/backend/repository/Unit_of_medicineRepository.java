package com.cpe.backend.repository;

import com.cpe.backend.Entity.Unit_of_medicine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface Unit_of_medicineRepository extends JpaRepository<Unit_of_medicine, Long> {
    Unit_of_medicine findById(long id);
}