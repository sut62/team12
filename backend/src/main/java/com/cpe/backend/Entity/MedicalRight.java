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
@Table(name = "MedicalRight")
public class MedicalRight {

    @Id
    @SequenceGenerator(name = "MedicalRight_SEQ", sequenceName = "MedicalRight_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MedicalRight_SEQ")
    @Column(name = "MedicalRight_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String MedicalRight;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<RecordBill> recordBill;
}