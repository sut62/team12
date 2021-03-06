package com.cpe.backend.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Unit_of_medicine")
public class Unit_of_medicine {

    @Id
    @SequenceGenerator(name = "Unit_of_medicine_SEQ", sequenceName = "Unit_of_medicine_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Unit_of_medicine_SEQ")
    @Column(name = "Unit_of_medicine_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String unit;

}