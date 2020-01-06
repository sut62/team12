package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "TitlePharmacist")
public class TitlePharmacist {

    @Id 
    @SequenceGenerator(name = "TitlePharmacist_SEQ", sequenceName = "TitlePharmacist_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TitlePharmacist_SEQ")
    @Column(name = "TitlePharmacist_ID", unique = true, nullable = true)
    
   
    private @NonNull Long id;
    private @NonNull String titlepharmacist;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Pharmacist> info;

}