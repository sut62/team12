package com.cpe.backend.repository;  
  
import com.cpe.backend.entity.MedicineLabel;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource  
public interface MedicineLabelRepository extends JpaRepository<MedicineLabel, Long> {
    MedicineLabel findById(long id);
}