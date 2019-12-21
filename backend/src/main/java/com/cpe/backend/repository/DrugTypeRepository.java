package com.cpe.backend.repository;

import com.cpe.backend.entity.DrugType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface DrugTypeRepository extends JpaRepository<DrugType, Long> {
	DrugType findById(long id);
}