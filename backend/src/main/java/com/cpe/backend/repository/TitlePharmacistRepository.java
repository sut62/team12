package com.cpe.backend.repository;

import com.cpe.backend.Entity.TitlePharmacist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface TitlePharmacistRepository extends JpaRepository<TitlePharmacist, Long> {
	TitlePharmacist findById(long id);
}