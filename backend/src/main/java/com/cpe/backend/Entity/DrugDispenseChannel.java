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
@NoArgsConstructor
@Table(name="DRUGDISPENSECHANNEL")
public class DrugDispenseChannel {

    @Id
    @SequenceGenerator(name="drugdispensechannel_seq",sequenceName="drugdispensechannel_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="drugdispensechannel_seq")
    @Column(name="DRUGDISPENSECHANNEL_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull Integer Channel;
}