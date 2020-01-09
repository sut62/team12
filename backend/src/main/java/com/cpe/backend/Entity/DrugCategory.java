package com.cpe.backend.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;



@Data
@Entity
@NoArgsConstructor
@Table(name="DRUGCATEGORY")
public class DrugCategory {

    @Id
    @SequenceGenerator(name="DRUGCATEGORY_SEQ",sequenceName="DRUGCATEGORY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DRUGCATEGORY_SEQ")
    @Column(name="DrugCategory_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String category;

    @OneToMany(fetch = FetchType.EAGER)
    //mappedBy  = "drugcategory"
    private Collection<Drug> drugcategory;
}

