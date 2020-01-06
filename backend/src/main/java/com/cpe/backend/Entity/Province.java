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
@Table(name = "Province")
public class Province {

    @Id 
    @SequenceGenerator(name = "Province_SEQ", sequenceName = "Province_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Province_SEQ")
    @Column(name = "Province_ID", unique = true, nullable = true)
    
   
    private @NonNull Long id;
    private @NonNull String province;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Pharmacist> info;
}