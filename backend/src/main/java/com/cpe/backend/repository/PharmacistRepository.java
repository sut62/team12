package com.cpe.backend.repository;

import com.cpe.backend.Entity.Pharmacist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
	Pharmacist findById(long id);
}