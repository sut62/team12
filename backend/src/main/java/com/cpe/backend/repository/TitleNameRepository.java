package com.cpe.backend.repository;

import com.cpe.backend.Entity.TitleName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TitleNameRepository extends JpaRepository<TitleName, Long> {
    TitleName findById(long id);
}