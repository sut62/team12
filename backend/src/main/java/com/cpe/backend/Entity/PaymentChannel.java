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
@Table(name = "PaymentChannel")
public class PaymentChannel {

    @Id
    @SequenceGenerator(name = "PaymentChannel_SEQ", sequenceName = "PaymentChannel_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PaymentChannel_SEQ")
    @Column(name = "PaymentChannel_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String Channel;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<RecordBill> recordBill;
}