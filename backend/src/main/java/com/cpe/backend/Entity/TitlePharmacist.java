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

   

}