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
@Table(name = "Cashier")
public class Cashier {

    @Id
    @SequenceGenerator(name = "Cashier_SEQ", sequenceName = "Cashier_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Cashier_SEQ")
    @Column(name = "Cashier_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<RecordBill> recordBill;
}