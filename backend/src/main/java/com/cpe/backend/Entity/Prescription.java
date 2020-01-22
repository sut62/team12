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
import lombok.Data;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
@Data
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Prescription")
public class Prescription {

    @Id
    @SequenceGenerator(name = "Prescription_SEQ", sequenceName = "Prescription_SEQ", initialValue=120000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Prescription_SEQ")
    @Column(name = "Prescription_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Pattern(regexp = "HN\\d{8}")
    private @NotNull String patient_ID;
    
    @Size(max=50,min=5)
    private @NotNull String name;

    @Min(1)
    @Max(122)
    private @NotNull Integer age;

    @Pattern(regexp = "[a-z]*")
    private @NotNull String drugallergy;

    @Min(1)
    @Max(100)
    private @NotNull Integer amount;
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

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Drug.class)
    @JoinColumn(name = "Drug_ID", insertable = true)
    private Drug drug;

}