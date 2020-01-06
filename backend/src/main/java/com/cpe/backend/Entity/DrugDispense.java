package com.cpe.backend.entity;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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
@Table(name = "DrugDispense")
public class DrugDispense {

    @Id
    @SequenceGenerator(name="drugdispense_seq",sequenceName="drugdispense_seq", initialValue=20000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="drugdispense_seq")
    @Column(name = "DRUGDISPENSE_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="Patient_ID")
    private @NonNull String Patient_ID;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DrugDispenseChannel.class)
    @JoinColumn(name = "DrugDispenseChannel_ID", insertable = true)
    private DrugDispenseChannel drugdispensechannel;
  
}