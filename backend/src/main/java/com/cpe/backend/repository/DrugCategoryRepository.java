package com.cpe.backend.repository;

import com.cpe.backend.entity.DrugCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface DrugCategoryRepository extends JpaRepository<DrugCategory, Long> {
	DrugCategory findById(long id);
}