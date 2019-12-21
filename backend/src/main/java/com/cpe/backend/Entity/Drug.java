package com.cpe.backend.entity;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Drug")
public class Drug {

    @Id
    @SequenceGenerator(name="drug_seq",sequenceName="drug_seq", initialValue=10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="drug_seq")
    @Column(name = "DRUG_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="DRUGNAME")
    private @NonNull String drugname;

    @Column(name="HOW")
    private @NonNull String how;

    @Column(name="SIDEEFFECT")
    private @NonNull String sideeffect;

    @Column(name="PRICE")
    private @NonNull Number price;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DrugType.class)
    @JoinColumn(name = "DrugType_ID", insertable = true)
    private DrugType drugtype;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DrugCategory.class)
    @JoinColumn(name = "DrugCategory_ID", insertable = true)
    private DrugCategory drugcategorType;

  
}