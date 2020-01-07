package com.cpe.backend.repository;

import com.cpe.backend.entity.DrugDispenseChannel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface DrugDispenseChannelRepository extends JpaRepository<DrugDispenseChannel, Long> {
	DrugDispenseChannel findById(long id);
}