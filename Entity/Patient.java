package com.cpe.backend.b5903900_register.entity;

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
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Patient")
public class Patient {

  @Id
  @SequenceGenerator(name = "Patient_SEQ", sequenceName = "Patient_SEQ" ,initialValue = 10001)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Patient_SEQ")
  @Column(name = "Patient_ID", unique = true, nullable = true)

  private @NonNull Long id;
  private @NonNull Long id_card;
  private @NonNull String Firstname;
  private @NonNull String Lastname;
  private @NonNull Integer Age;
  private @NonNull Date   Birthday;
  private @NonNull String Address;
  private @NonNull String Drugallergy;
  private @NonNull String Congenitaldisease;
  

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
  @JoinColumn(name = "Gender_ID", insertable = true)
  private Gender gender;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = TitleName.class)
  @JoinColumn(name = "TitleName_ID", insertable = true)
  private TitleName titleName;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = BloodGroup.class)
  @JoinColumn(name = "BloodGroup_ID", insertable = true)
  private BloodGroup bloodGroup;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
  @JoinColumn(name = "Province_ID", insertable = true)
  private Province province;

  
}