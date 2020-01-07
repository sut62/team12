package com.cpe.backend.repository;  
  
import com.cpe.backend.entity.MedicineQuantity;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource  
public interface MedicineQuantityRepository extends JpaRepository<MedicineQuantity, Long> {
    MedicineQuantity findById(long id);
}