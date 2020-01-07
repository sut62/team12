package com.cpe.backend.repository;  
  
import com.cpe.backend.entity.MedicineFrequency;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource  
public interface MedicineFrequencyRepository extends JpaRepository<MedicineFrequency, Long> {
    MedicineFrequency findById(long id);
}