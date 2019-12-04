package com.okta.springbootvue.Entity;

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
  @SequenceGenerator(name = "Examination_SEQ", sequenceName = "Examination_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Examination_SEQ")
  @Column(name = "Examination_ID", unique = true, nullable = true)

  private @NonNull Long id;
  private @NonNull String result;
  private @NonNull Date createDate;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Disease.class)
  @JoinColumn(name = "Disease_ID", insertable = true)
  private Disease disease_name;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Treatment_Type.class)
  @JoinColumn(name = "Type_ID", insertable = true)
  private Treatment_Type type_name;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Severity_Level.class)
  @JoinColumn(name = "Level_ID", insertable = true)
  private Severity_Level level;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Patient.class)
  @JoinColumn(name = "Patient_ID", insertable = true)
  private Patient patient;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = DoctorProfile.class)
  @JoinColumn(name = "Doctor_ID", insertable = true)
  private DoctorProfile doctor;
}