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
@Table(name = "Prescription")
public class Prescription {

    @Id
    @SequenceGenerator(name = "Prescription_SEQ", sequenceName = "Prescription_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Prescription_SEQ")
    @Column(name = "Prescription_ID", unique = true, nullable = true)

    private @NonNull Long id;
    private @NonNull String patient_ID;
    private @NonNull String name;
    private @NonNull Integer age;
    private @NonNull String drugallergy;
    private @NonNull Integer amount;
    private @NonNull Date createDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TitleName.class)
    @JoinColumn(name = "TitleName_ID", insertable = true)
    private TitleName titleName;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Doctor.class)
    @JoinColumn(name = "Doctor_ID", insertable = true)
    private Doctor doctor_name;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Unit_of_medicine.class)
    @JoinColumn(name = "Unit_of_medicine_ID", insertable = true)
    private Unit_of_medicine unit_of_medicine;
}