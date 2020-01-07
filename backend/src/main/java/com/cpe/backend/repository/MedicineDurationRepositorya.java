package com.cpe.backend.repository;  
  
import com.cpe.backend.entity.MedicineDuration;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource  
public interface MedicineDurationRepository extends JpaRepository<MedicineDuration, Long> {
    MedicineDuration findById(long id);
}