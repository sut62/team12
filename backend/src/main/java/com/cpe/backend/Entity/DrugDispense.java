package com.cpe.backend.Entity;
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
import javax.validation.constraints.*;

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
    private @NotNull Long id;

    @Size(min=2,max=30)
    @Pattern(regexp = "[a-zA-Zก-๙\\s]*")
    private @NotNull String reciever_name;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DrugDispenseChannel.class)
    @JoinColumn(name = "DrugDispenseChannel_ID", insertable = true)
    private DrugDispenseChannel drugdispensechannel;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Pharmacist.class)
    @JoinColumn(name = "Pharmacist_ID", insertable = true)
    private Pharmacist pharmacist;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TitlePharmacist.class)
    @JoinColumn(name = "TitlePharmacist_ID",insertable = true)
    private TitlePharmacist TitlePharmacist ;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Prescription.class)
    @JoinColumn(name = "Prescription_ID",insertable = true)
    private Prescription prescription;
}