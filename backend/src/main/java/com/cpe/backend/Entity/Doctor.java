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
@Table(name = "Doctor")
public class Doctor {

    @Id
    @SequenceGenerator(name = "Doctor_SEQ", sequenceName = "Doctor_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Doctor_SEQ")
    @Column(name = "Doctor_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String doctor_name;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Prescription> doctor;
}