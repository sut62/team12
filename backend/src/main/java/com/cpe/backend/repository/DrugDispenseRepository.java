package com.cpe.backend.repository;

import com.cpe.backend.Entity.DrugDispense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface DrugDispenseRepository extends JpaRepository<DrugDispense, Long> {
	DrugDispense findById(long id);
}