package com.cpe.backend.repository;

import com.cpe.backend.Entity.Cashier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CashierRepository extends JpaRepository<Cashier, Long> {
    Cashier findById(long id);
}