package com.cpe.backend.repository;

import com.cpe.backend.Entity.Drug;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface DrugRepository extends JpaRepository<Drug, Long> {
    public List<Drug> findByDrugname(String drugname);
    Drug findById(long id);
}