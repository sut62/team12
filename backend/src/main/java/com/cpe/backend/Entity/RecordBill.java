package com.cpe.backend.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;
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
@Table(name = "RecordBill")
public class RecordBill {

    @Id
    @SequenceGenerator(name = "RecordBill_SEQ", sequenceName = "RecordBill_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RecordBill_SEQ")
    @Column(name = "RecordBill_ID", unique = true, nullable = true)

    private @NonNull Long ID;
    private @NonNull Integer TotalPrice ;
    private @NonNull Date createDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PaymentChannel.class)
    @JoinColumn(name = "PaymentChannel_ID", insertable = true)
    private PaymentChannel paymentChannel;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Cashier.class)
    @JoinColumn(name = "Cashier_ID", insertable = true)
    private Cashier cashier;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MedicalRight.class)
    @JoinColumn(name = "MedicalRight_ID", insertable = true)
    private MedicalRight medicalRight;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Prescription.class)
    @JoinColumn(name = "Prescription_ID",insertable = true)
    private Prescription prescription;
}