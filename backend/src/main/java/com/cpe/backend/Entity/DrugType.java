package com.cpe.backend.entity;

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
@Table(name="DRUGTYPE")
public class DrugType {

    @Id
    @SequenceGenerator(name="DRUGTYPE_SEQ",sequenceName="DRUGTYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DRUGTYPE_SEQ")
    @Column(name="DRUGTYPE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String drugtypename;

    @OneToMany(fetch = FetchType.EAGER)
    //mappedBy  = "drugtype"
    private Collection<Drug> dr;
}