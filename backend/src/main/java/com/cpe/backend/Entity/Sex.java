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
@Table(name = "Sex")
public class Sex {

    @Id 
    @SequenceGenerator(name = "Sex_SEQ", sequenceName = "Sex_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sex_SEQ")
    @Column(name = "Sex_ID", unique = true, nullable = true)
    
   
    private @NonNull Long id;
    private @NonNull String sex;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Pharmacist> info;
}